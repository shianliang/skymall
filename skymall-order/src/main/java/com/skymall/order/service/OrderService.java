/**  
 * <p>Title: OrderService.java</p>  
 * <p>Description: </p>   
 * @author shianliang  
 * @date 2018年5月20日  
 * @version 1.0  
 */ 
package com.skymall.order.service;

import java.util.List;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.pojo.TbOrder;
import com.skymall.pojo.TbOrderItem;
import com.skymall.pojo.TbOrderShipping;

/**  
 * <p>Title: OrderService</p>  
 * <p>Description: </p>  
 * @author shianliang 
 * @date 2018年5月20日  
 */
public interface OrderService {

	SkymallResult createOrder(TbOrder order,List<TbOrderItem> orderItemList,TbOrderShipping orderShipping);
}
