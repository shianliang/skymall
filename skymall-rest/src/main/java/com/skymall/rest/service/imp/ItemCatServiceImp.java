package com.skymall.rest.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skymall.dao.TbItemCatDao;
import com.skymall.pojo.TbItemCat;
import com.skymall.pojo.TbItemCatQuery;
import com.skymall.pojo.TbItemCatQuery.Criteria;
import com.skymall.rest.pojo.CategoryNode;
import com.skymall.rest.pojo.CategoryResult;
import com.skymall.rest.service.ItemCatService;
@Service
public class ItemCatServiceImp  implements ItemCatService{
	
	@Autowired
	private TbItemCatDao itemCatDao;
	
	/**
	 * 获取商品分类（拼装成前端需要的格式）
	 */
	@Override
	public CategoryResult categoryResult() {
	
		CategoryResult categoryResult=new CategoryResult();
		categoryResult.setData(getCategoryNode(0));
	    return categoryResult;
	}
	/**
	 * 
	* @Title: 获取节点 （拼装成前端需要的格式）
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param parentId
	* @param @return    入参
	* @return List<?>    返回类型
	* @author （作者） 
	* @throws
	* @date 2018年3月4日 下午2:25:12 
	* @version V1.0
	 */
	public List<?> getCategoryNode(long parentId){
		
		TbItemCatQuery query=new TbItemCatQuery();
		Criteria criteria=query.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> itemCatList = itemCatDao.selectByExample(query);
		List resultList=new ArrayList();
		int count=0;
		for (TbItemCat itemCat : itemCatList) {
			CategoryNode categoryNode=new CategoryNode();
		
			if(itemCat.getIsParent()){//筛选叶子节点
				if(itemCat.getParentId()==0){
					
					categoryNode.setName("<a href='/products/"+itemCat.getId()+".html'>"+itemCat.getName()+"</a>");	
				}else{
					categoryNode.setName(itemCat.getName());
				}
				categoryNode.setUrl("/products/"+itemCat.getId()+".html");
				categoryNode.setItems(getCategoryNode(itemCat.getId()));
				resultList.add(categoryNode);
				//前端页面只显示14个分类   多了超出框
				count++;
				if(itemCat.getParentId()==0&&count>=14){
					break;
				}
			
			}else{  //叶子节点
				resultList.add("/products/"+itemCat.getId()+".html|"+itemCat.getName());
			}
		}

		return resultList;
	}
}
