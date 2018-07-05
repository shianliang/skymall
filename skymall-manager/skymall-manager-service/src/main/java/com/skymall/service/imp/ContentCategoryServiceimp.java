package com.skymall.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.common.pojo.TreeNode;
import com.skymall.dao.TbContentCategoryDao;
import com.skymall.pojo.TbContentCategory;
import com.skymall.pojo.TbContentCategoryQuery;
import com.skymall.pojo.TbContentCategoryQuery.Criteria;
import com.skymall.service.ContentCategoryService;

/**
 * 内容分类管理
* @ClassName: ContentCategoryimp 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author shianliang 
* @date 2018年3月9日 下午2:16:56 
* @version V1.0
 */
@Service
public class ContentCategoryServiceimp implements ContentCategoryService {

	@Autowired
	private TbContentCategoryDao contentCategoryDao;
	
	@Override
	public List<TreeNode> getContentCategoryList(long parentId) {
		
		TbContentCategoryQuery contentCategoryQuery=new TbContentCategoryQuery();
		
		Criteria criteria=contentCategoryQuery.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> contentCategories=contentCategoryDao.selectByExample(contentCategoryQuery);
		List<TreeNode> resultList=new ArrayList<>();
		for (TbContentCategory tbContentCategory : contentCategories) {
			TreeNode treeNode=new TreeNode();
			treeNode.setText(tbContentCategory.getName());
			treeNode.setId(tbContentCategory.getId());
			treeNode.setState(tbContentCategory.getIsParent()?"closed":"open");
			resultList.add(treeNode);
		}
		
		return resultList;
	}
	
	/**
	 * 新增内容分类
	 * categoryName 分类名称
	 * parentId 父级id
	 * 
	 * return  分类id
	 */
	@Override
	public SkymallResult insertContentCategory(String categoryName, long parentId) {
		
		TbContentCategory contentcategory=new TbContentCategory();
		contentcategory.setName(categoryName);
		contentcategory.setCreated(new Date());
		contentcategory.setUpdated(new Date());
		contentcategory.setIsParent(false);
		contentcategory.setParentId(parentId);
		contentcategory.setSortOrder(1);
		contentcategory.setStatus(1);
		
		contentCategoryDao.insert(contentcategory);
		
		// 获取父级分类，判断父级分类是否是叶子节点，如果是则需要修改isPrent字段为true，否则不做修改
		
		TbContentCategory categoryParent=contentCategoryDao.selectByPrimaryKey(parentId);
		if(!categoryParent.getIsParent()){
			categoryParent.setUpdated(new Date());
			categoryParent.setIsParent(true);
			contentCategoryDao.updateByPrimaryKey(categoryParent);
		}
		//返回刚插入分类的id
		return SkymallResult.ok(contentcategory);
	}

}
