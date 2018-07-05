package com.skymall.service;

import java.util.List;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.common.pojo.TreeNode;
import com.skymall.pojo.TbContentCategory;

public interface ContentCategoryService {

	List<TreeNode> getContentCategoryList(long parentId);
	
	SkymallResult insertContentCategory(String categoryName ,long parentId);
}
