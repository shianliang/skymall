package com.skymall.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skymall.common.pojo.TreeNode;
import com.skymall.dao.TbItemCatDao;
import com.skymall.pojo.TbItemCat;
import com.skymall.pojo.TbItemCatQuery;
import com.skymall.pojo.TbItemCatQuery.Criteria;
import com.skymall.service.ItemCatService;
@Service
public class ItemCatServiceImp implements ItemCatService{

	@Autowired
	private TbItemCatDao itemCatDao;
	
	public List<TreeNode> getTreeNodeList(long parentId){
		TbItemCatQuery itemCatQuery=new TbItemCatQuery();
		Criteria criteria = itemCatQuery.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> itemCats=itemCatDao.selectByExample(itemCatQuery);
		List<TreeNode> treeNodeList=new ArrayList<TreeNode>();
		if(itemCats!=null&&itemCats.size()>0){
			for (TbItemCat itemCat : itemCats) {
				TreeNode treeNode=new TreeNode();
				treeNode.setId(itemCat.getId());
				treeNode.setText(itemCat.getName());
				treeNode.setState(itemCat.getIsParent()?"closed":"open");
				treeNodeList.add(treeNode);
			}
		}
		return treeNodeList;
	}
	
}
