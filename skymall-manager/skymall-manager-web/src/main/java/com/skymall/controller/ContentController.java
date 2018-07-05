package com.skymall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skymall.common.pojo.DataResult;
import com.skymall.common.pojo.SkymallResult;
import com.skymall.pojo.TbContent;
import com.skymall.service.ContentService;

/**
 * 商品内容（前台展示，推荐商品、促销商品等等）
* @ClassName: ContentController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author (作者)  
* @date 2018年3月9日 下午4:26:33 
* @version V1.0
 */
@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/content/query/list")
	@ResponseBody
	public DataResult contentList(long categoryId,int page,int rows){
		
		return contentService.getContentList(categoryId, page, rows);
		
	}
	
	@RequestMapping("/content/save")
	@ResponseBody
	public SkymallResult saveContent(TbContent content){
		
		return contentService.saveContent(content);
	}
	
	@RequestMapping("/rest/content/edit")
	@ResponseBody
	public SkymallResult editContent(TbContent content){
		
		return contentService.editContent(content);
	}
	
}
