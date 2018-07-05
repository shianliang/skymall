/**  
 * <p>Title: CartService.java</p>  
 * <p>Description: </p>   
 * @author shianliang  
 * @date 2018年5月13日  
 * @version 1.0  
 */ 
package com.skymall.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.portal.pojo.CartItem;

/**  
 * 购物车
 * <p>Title: CartService</p>  
 * <p>Description: </p>  
 * @author shianliang 
 * @date 2018年5月13日  
 */
public interface CartService {

	/**
	 * 添加购物车
	 * <p>Description: </p> 
	 * @author shianliang  
	 * @date 2018年5月13日  
	 * @version 1.0
	 * @param itemId
	 * @param num
	 * @param request
	 * @param response
	 * @return
	 */
	SkymallResult addCart(long itemId,int num,HttpServletRequest request, HttpServletResponse response);
	
	
	List<CartItem> getCartItem(HttpServletRequest request);
	
	SkymallResult delCartItem(long itemId,HttpServletRequest request, HttpServletResponse response);
}
