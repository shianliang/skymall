package com.skymall.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.rest.service.ItemService;
/**
 * 
 * <p>Title: ItemController</p>  
 * <p>Description: </p>  
 * @author shianliang 
 * @date 2018年4月22日
 */
@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	/**
	 * 
	 * <p>Title: findItemInfoByItemId</p>
	 * <p>Description: 通过商品ID查询商品描述 </p>  
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/info/{itemId}")
	@ResponseBody
	public SkymallResult findItemInfoByItemId(@PathVariable("itemId")long itemId){
		
		SkymallResult result=itemService.findItemInfoById(itemId);
		
		return result;
		
	}


	/**
	 * 
	 * <p>Title: findItemDescByItemId</p>
	 * <p>Description:通过商品ID查询商品描述 </p> 
	 * @author shianliang  
	 * @date 2018年4月22日  
	 * @version 1.0  
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public SkymallResult findItemDescByItemId(@PathVariable("itemId")long itemId){
		
		SkymallResult result=itemService.findItemDescByItemId(itemId);
		
		return result;
		
	}
	
	/**
	 * 
	 * <p>Title: findItemParamByItemId</p>
	 * <p>Description:通过商品ID查询商品规格参数 </p> 
	 * @author shianliang  
	 * @date 2018年4月22日  
	 * @version 1.0  
	 * @param itemId  商品ID
	 * @return
	 */
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public SkymallResult findItemParamByItemId(@PathVariable("itemId")long itemId){
		
		SkymallResult result=itemService.findItemParamByItmId(itemId);
		
		return result;
		
	}
}
