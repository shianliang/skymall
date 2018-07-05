package com.skymall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skymall.common.pojo.DataResult;
import com.skymall.common.pojo.SkymallResult;
import com.skymall.dao.TbItemDao;
import com.skymall.pojo.TbItem;


public interface ItemService {

	/**
	 * 
	 * <p>Description:通过商品ID获取商品 </p> 
	 * @author shianliang  
	 * @date 2018年4月22日  
	 * @version 1.0
	 * @param id
	 * @return
	 */
	TbItem getItemById(Long id);
	/**
	 * 
	 * <p>Description:商品列表 </p> 
	 * @author shianliang  
	 * @date 2018年4月22日  
	 * @version 1.0
	 * @param page
	 * @param rows
	 * @return
	 */
	DataResult getItemList(int page,int rows);
	/**
	 * 
	 * <p>Description: 保存商品 </p> 
	 * @author shianliang  
	 * @date 2018年4月22日  
	 * @version 1.0
	 * @param item
	 * @param desc
	 * @param param
	 * @return
	 * @throws Exception
	 */
	SkymallResult saveItme(TbItem item,String desc ,String param) throws Exception;
	
	/**
	 * 通过商品ID删除
	 * <p>Description: </p> 
	 * @author shianliang  
	 * @date 2018年7月3日  
	 * @version 1.0
	 * @param itemId
	 * @return
	 */
	boolean deleteItemById(Long itemId);
	
	
}
