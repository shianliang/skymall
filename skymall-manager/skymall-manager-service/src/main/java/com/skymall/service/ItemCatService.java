package com.skymall.service;

import java.util.List;

import com.skymall.common.pojo.TreeNode;

public interface ItemCatService {

	List<TreeNode> getTreeNodeList(long parentId);
	
}
