package com.skymall.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skymall.common.pojo.DataResult;
import com.skymall.common.pojo.SkymallResult;
import com.skymall.common.utils.JsonUtils;
import com.skymall.pojo.TbItem;
import com.skymall.service.ItemService;


@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	/**
	 * 
	 * <p>Title: getItemById</p>
	 * <p>Description:通过商品id获取商品 </p> 
	 * @author shianliang  
	 * @date 2018年4月22日  
	 * @version 1.0  
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId){
	TbItem item=itemService.getItemById(itemId);
	System.err.println(item);
		return item;
		
	}
	/**
	 * 
	 * <p>Title: getItemList</p>
	 * <p>Description: 商品列表</p> 
	 * @author shianliang  
	 * @date 2018年4月22日  
	 * @version 1.0  
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/items")
	@ResponseBody
	public DataResult getItemList(int page , int rows){
		DataResult result=itemService.getItemList(page, rows);
		return result;
	}
	
	/**
	 * 
	 * <p>Title: saveItem</p>
	 * <p>Description: 保存商品</p> 
	 * @author shianliang  
	 * @date 2018年4月22日  
	 * @version 1.0  
	 * @param item
	 * @param desc
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/item/save" ,method=RequestMethod.POST)
	@ResponseBody
	public SkymallResult saveItem(TbItem item,String desc,String itemParams) throws Exception{
		
		SkymallResult result=itemService.saveItme(item ,desc,itemParams);
		
		return result;
	}
	/**
	 * 通过商品id删除商品
	 * <p>Description: </p> 
	 * @author shianliang  
	 * @date 2018年7月3日  
	 * @version 1.0
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/rest/item/delete",method=RequestMethod.POST)
	@ResponseBody
	public SkymallResult deleteById(String ids){
		
		if(StringUtils.isBlank(ids)){
			return SkymallResult.build(400,"删除的id为空");	
		}
		try {
		String[] itemIds=ids.split(",");
			for (String id : itemIds) {
				long itemid=Long.parseLong(id);
				itemService.deleteItemById(itemid);
			}
		} catch (Exception e) {
			return SkymallResult.build(400,"删除失败");
		}
		
		
		return SkymallResult.ok();
	}
	
}
