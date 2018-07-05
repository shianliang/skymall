/**  
 * <p>Title: ItemService.java</p>  
 * <p>Description: </p>   
 * @author shianliang  
 * @date 2018年4月22日  
 * @version 1.0  
 */ 
package com.skymall.portal.service;

import com.skymall.pojo.TbItem;
import com.skymall.portal.pojo.Item;
import com.skymall.portal.pojo.ItemInfo;

/**  
 * <p>Title: ItemService</p>  
 * <p>Description:商品信息service </p>  
 * @author shianliang 
 * @date 2018年4月22日  
 */
public interface ItemService {

	/**
	 * 
	 * <p>Title: getItemInfoByItemId</p>
	 * <p>Description: 根据商品id获取商品信息</p> 
	 * @author shianliang  
	 * @date 2018年4月22日  
	 * @version 1.0  
	 * @param itemId
	 * @return
	 */
	ItemInfo getItemInfoByItemId(long itemId);
	/**
	 * 
	 * <p>Title: getItemDescByItemId</p>
	 * <p>Description:根据商品ID查询商品描述 </p> 
	 * @author shianliang  
	 * @date 2018年4月22日  
	 * @version 1.0  
	 * @param itemId
	 * @return
	 */
	String getItemDescByItemId(long itemId);
	
	/**
	 * 
	 * <p>Description:根据商品ID查询商品规格 </p> 
	 * @author shianliang  
	 * @date 2018年4月23日  
	 * @version 1.0
	 * @param itemId
	 * @return
	 */
	String getItemParamItem(long itemId);
}
