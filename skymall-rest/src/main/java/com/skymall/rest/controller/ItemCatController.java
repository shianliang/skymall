package com.skymall.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skymall.common.utils.JsonUtils;
import com.skymall.rest.pojo.CategoryResult;
import com.skymall.rest.service.ItemCatService;
/**
 * 
* @ClassName: 商城首页商品分类 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author (作者)  
* @date 2018年3月4日 下午12:51:15 
* @version V1.0
 */
@Controller
class ItemCatController {

	@Autowired
	private ItemCatService  itemCatService;
	
	/**
	 * produces是RequestMapping注解中设置返回值类型的属性
	 */
	@RequestMapping(value="/itemcat/list",produces=MediaType.APPLICATION_JSON_VALUE+"; charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback){
		CategoryResult categoryResult = itemCatService.categoryResult();
		String resultJson=JsonUtils.objectToJson(categoryResult);
		resultJson=callback+"("+resultJson+");";
		return resultJson;
	}
	
}
