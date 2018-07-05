/**  
 * <p>Title: Order.java</p>  
 * <p>Description: </p>   
 * @author shianliang  
 * @date 2018年5月20日  
 * @version 1.0  
 */ 
package com.skymall.order.pojo;

import java.util.List;

import com.skymall.pojo.TbOrder;
import com.skymall.pojo.TbOrderItem;
import com.skymall.pojo.TbOrderShipping;

/**  
 * <p>Title: Order</p>  
 * <p>Description: </p>  
 * @author shianliang 
 * @date 2018年5月20日  
 */
public class Order extends TbOrder{
	
	private List<TbOrderItem> orderItems;
	
	private TbOrderShipping orderShipping;

	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}

	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	
	

}
