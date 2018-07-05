/**  
 * <p>Title: OrderController.java</p>  
 * <p>Description: </p>   
 * @author shianliang  
 * @date 2018年5月20日  
 * @version 1.0  
 */ 
package com.skymall.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.pojo.TbUser;
import com.skymall.portal.pojo.CartItem;
import com.skymall.portal.pojo.Order;
import com.skymall.portal.service.CartService;
import com.skymall.portal.service.OrderService;

/**  
 * <p>Title: OrderController</p>  
 * <p>Description: </p>  
 * @author shianliang 
 * @date 2018年5月20日  
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/order-cart")
	public String orderCart(HttpServletRequest request,Model model){
		
		List<CartItem> list=cartService.getCartItem(request);
		model.addAttribute("cartList", list);
		return "order-cart";
	}
	
	/**
	 * 创建订单
	 * <p>Description: </p> 
	 * @author shianliang  
	 * @date 2018年5月21日  
	 * @version 1.0
	 * @param order
	 * @param model
	 * @return
	 */
	@RequestMapping("/create")
	public String createOrder(Order order,Model model,HttpServletRequest request){
		try {
			//将拦截器中放入request中的user取出
			TbUser user=(TbUser) request.getAttribute("user");
			//order补全user信息
			order.setUserId(user.getId());
			order.setBuyerNick(user.getUsername());

			String orderId=orderService.createOrder(order);
			//将页面需要的参数返回
			model.addAttribute("orderId",orderId);
			model.addAttribute("payment", order.getPayment());
			model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
			
			return "success";
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message","创建订单失败！请重试");
			return "error/exception";
		}
	}
}
