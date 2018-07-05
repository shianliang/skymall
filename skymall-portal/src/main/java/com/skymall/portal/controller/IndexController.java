package com.skymall.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skymall.portal.service.ContentService;

/**
 * 
* @ClassName: IndexController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author shianliang  
* @date 2018年1月23日 下午8:05:21 
* @version V1.0
 */
@Controller
public class IndexController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/index")
	public String index(Model model){
		String adJson=contentService.getContentList();
		model.addAttribute("ad1", adJson);
		
		return "index";
	}
}
