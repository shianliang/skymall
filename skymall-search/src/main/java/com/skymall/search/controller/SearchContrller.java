package com.skymall.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctc.wstx.util.StringUtil;
import com.skymall.common.pojo.SkymallResult;
import com.skymall.common.utils.ExceptionUtil;
import com.skymall.search.pojo.SearchResult;
import com.skymall.search.service.SearchService;

@Controller
public class SearchContrller {

	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/query")
	@ResponseBody
	public SkymallResult search(@RequestParam("q") String query,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="60")int rows){
		
		if(StringUtils.isBlank(query)){
			return SkymallResult.build(400, "查询条件不能为空");
		}
		SearchResult searchResult=null;
		try {
			//url参数格式化
			String urlParam=new String(query.getBytes("iso8859-1"),"utf-8");
			
			searchResult = searchService.search(urlParam, page, rows);
			
		} catch (Exception e) {
			e.printStackTrace();
			return SkymallResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return SkymallResult.ok(searchResult); 
		
	}
	
	
}
