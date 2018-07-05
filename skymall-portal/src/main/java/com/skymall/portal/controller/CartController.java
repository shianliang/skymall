/**  
 * <p>Title: CartController.java</p>  
 * <p>Description: </p>   
 * @author shianliang  
 * @date 2018年5月13日  
 * @version 1.0  
 */ 
package com.skymall.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.portal.pojo.CartItem;
import com.skymall.portal.service.CartService;

/**  
 * <p>Title: CartController</p>  
 * <p>Description: </p>  
 * @author shianliang 
 * @date 2018年5月13日  
 */
@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@RequestMapping("/add/{itemId}")
	public String addCart(@PathVariable Long itemId,@RequestParam(defaultValue="1") Integer num,HttpServletRequest request,HttpServletResponse response){
	
		SkymallResult result = cartService.addCart(itemId, num, request, response);
		
		return "cartSuccess";
	}
	
	@RequestMapping("/cart")
	public String getCartList(HttpServletRequest request,Model model){
		List<CartItem> cartList=cartService.getCartItem(request);
		model.addAttribute("cartList",cartList);
		return "cart";
	}
	@RequestMapping("/delete/{itemId}")
	public String delCartItem(@PathVariable Long itemId,HttpServletRequest request, HttpServletResponse response){
		cartService.delCartItem(itemId, request, response);
		
		return "redirect:/cart/cart.html" ;
	}
}
