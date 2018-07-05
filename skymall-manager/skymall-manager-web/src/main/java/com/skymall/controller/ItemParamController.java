package com.skymall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skymall.common.pojo.DataResult;
import com.skymall.common.pojo.SkymallResult;
import com.skymall.common.utils.JsonUtils;
import com.skymall.pojo.TbItemParam;
import com.skymall.service.ItemParamService;

/**
 * 
 * <p>Title: ItemParamController</p>  
 * <p>Description: 规格参数controller</p>  
 * @author shianliang 
 * @date 2018年4月22日
 */
@Controller
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;
	
	/**
	 * 
	 * <p>Title: itemParamList</p>
	 * <p>Description:规格参数列表 </p> 
	 * @author shianliang  
	 * @date 2018年4月22日  
	 * @version 1.0  
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/item/param/list",method=RequestMethod.GET)
	@ResponseBody
	public DataResult itemParamList(int page,int rows){
		
		return itemParamService.itemParamList(page, rows);
		
	}
	
	/**
	 * 
	 * <p>Title: getItemParamByItemCatId</p>
	 * <p>Description:通过商品ID查询商品规格 </p> 
	 * @author shianliang  
	 * @date 2018年4月22日  
	 * @version 1.0  
	 * @param itemCatId
	 * @return
	 */
	@RequestMapping("/item/param/query/itemcatid/{itemcatid}")
	@ResponseBody
	public SkymallResult getItemParamByItemCatId(@PathVariable("itemcatid")long itemCatId ){
		
		return itemParamService.getItemParamByCatId(itemCatId);  	
	}
	/**
	 * 
	 * <p>Title: saveItemParam</p>
	 * <p>Description: 保存规格参数</p> 
	 * @author shianliang  
	 * @date 2018年4月22日  
	 * @version 1.0  
	 * @param cid
	 * @param paramData
	 * @return
	 */
	@RequestMapping("item/param/save/{cid}")
	@ResponseBody
	public SkymallResult saveItemParam(@PathVariable long cid,String paramData){
		TbItemParam itemParam=new TbItemParam();
		itemParam.setParamData(paramData);
		itemParam.setItemCatId(cid);
		return itemParamService.saveItemParam(itemParam);
	}
}
