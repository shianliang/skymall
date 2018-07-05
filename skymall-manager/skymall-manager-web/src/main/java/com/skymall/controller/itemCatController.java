package com.skymall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skymall.common.pojo.TreeNode;
import com.skymall.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class itemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<TreeNode> getItemCatList(@RequestParam(value="id",defaultValue="0")long parentId){
		return itemCatService.getTreeNodeList(parentId);
	}
	
}
