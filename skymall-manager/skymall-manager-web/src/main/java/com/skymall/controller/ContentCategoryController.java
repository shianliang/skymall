package com.skymall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.common.pojo.TreeNode;
import com.skymall.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<TreeNode> getContentCategoryList(@RequestParam(value="id",defaultValue="0") long parentId){
		List<TreeNode> contentCategoryList = contentCategoryService.getContentCategoryList(parentId);
		return contentCategoryList;
		
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public SkymallResult insertContentCategory(String name,long parentId){
		
		SkymallResult result = contentCategoryService.insertContentCategory(name, parentId);
		
		return result;
	}
	
}
