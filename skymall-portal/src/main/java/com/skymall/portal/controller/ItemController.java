/**  
 * <p>Title: ItemController.java</p>  
 * <p>Description: </p>   
 * @author shianliang  
 * @date 2018年4月22日  
 * @version 1.0  
 */ 
package com.skymall.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skymall.pojo.TbItem;
import com.skymall.portal.pojo.ItemInfo;
import com.skymall.portal.service.ItemService;

/**  
 * <p>Title: ItemController</p>  
 * <p>Description:商品 </p>  
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
	 * <p>Title: getItemInfoByItemId</p>
	 * <p>Description:通过商品ID查询商品 </p> 
	 * @author shianliang  
	 * @date 2018年4月22日  
	 * @version 1.0  
	 * @param itemId
	 * @param model
	 * @return
	 */
	@RequestMapping("/{id}")
	public String getItemInfoByItemId(@PathVariable("id")long itemId ,Model model) {
		ItemInfo item= itemService.getItemInfoByItemId(itemId);
		model.addAttribute("item",item);
		return "item";
	}
	
	@RequestMapping(value="/desc/{itemId}",produces="text/html;charset=utf-8")
	@ResponseBody
	public String  getItemDescByItemId(@PathVariable long itemId){
		
		return itemService.getItemDescByItemId(itemId);
		
	}
	
	@RequestMapping(value="/param/{itemId}",produces="text/html;charset=utf-8")
	@ResponseBody
	public String getItemParamItem(@PathVariable long itemId){
		
		return itemService.getItemParamItem(itemId);
	}
	
}
