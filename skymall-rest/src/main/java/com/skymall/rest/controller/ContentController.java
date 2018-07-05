package com.skymall.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.common.utils.ExceptionUtil;
import com.skymall.pojo.TbContent;
import com.skymall.rest.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/list/{contentCategoryId}")
	@ResponseBody
	public SkymallResult getContentList(@PathVariable("contentCategoryId") long categoryId){
		try{
			List<TbContent> contentList = contentService.getContentList(categoryId);
			return SkymallResult.ok(contentList);
		}catch(Exception e){
			String err=ExceptionUtil.getStackTrace(e);
			return SkymallResult.build(500, err);
		}
		
		
		
	}
	
	
	
}
