package com.skymall.portal.controller;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skymall.portal.pojo.SearchResult;
import com.skymall.portal.service.SearchService;
/**
 * 
 * <p>Title: SearchController</p>  
 * <p>Description:全文检索controller </p>  
 * @author shianliang 
 * @date 2018年4月22日
 */
@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/search")
	public String Search(@RequestParam("q")String query,@RequestParam(defaultValue="1")Integer page ,Model model){
		
		if(StringUtils.isNotBlank(query)){
			try {
				query=new String(query.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		SearchResult result = searchService.search(query, page);
		
		model.addAttribute("query",query);
		model.addAttribute("page",page);
		if(result!=null){
			model.addAttribute("totalPages",result.getPageCount());
			model.addAttribute("itemList",result.getItemList());
		}
		return "search";
	}
	
	
}