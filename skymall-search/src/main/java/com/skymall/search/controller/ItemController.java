package com.skymall.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.search.pojo.Item;
import com.skymall.search.service.ItemService;

@RequestMapping("/manager")
@Controller
public class ItemController {
	
	@Autowired
	private ItemService ItemService;
	
	@RequestMapping("/importall")
	@ResponseBody
	public SkymallResult importAllItems(){
		SkymallResult result=ItemService.importAllItems();
		return result;
	}
	
	
	
}
