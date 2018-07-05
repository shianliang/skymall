/**  
 * <p>Title: OrderController.java</p>  
 * <p>Description: </p>   
 * @author shianliang  
 * @date 2018年5月20日  
 * @version 1.0  
 */ 
package com.skymall.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.common.utils.ExceptionUtil;
import com.skymall.order.pojo.Order;
import com.skymall.order.service.OrderService;
import com.skymall.pojo.TbOrder;
import com.skymall.pojo.TbOrderItem;
import com.skymall.pojo.TbOrderShipping;

/**  
 * <p>Title: OrderController</p>  
 * <p>Description: </p>  
 * @author shianliang 
 * @date 2018年5月20日  
 */
@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public SkymallResult createOrder(@RequestBody Order order){
		
		if(null==order||null==order.getOrderItems()||order.getOrderItems().size()<1||null==order.getOrderShipping()){
			return SkymallResult.build(500, "参数异常");
		}
		try {
			SkymallResult result=orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());

			return  result;
		} catch (Exception e) {
			e.printStackTrace();
			return SkymallResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		
	}
	
}
