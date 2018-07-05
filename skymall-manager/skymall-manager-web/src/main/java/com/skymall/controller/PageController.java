package com.skymall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	/**
	 * 首页面
	 * @return
	 */
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
	/**
	 * 跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page){
		
		return page;
		
	}
	
}
