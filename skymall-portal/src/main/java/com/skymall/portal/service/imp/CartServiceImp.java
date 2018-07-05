/**  
 * <p>Title: CartServiceImp.java</p>  
 * <p>Description: </p>   
 * @author shianliang  
 * @date 2018年5月13日  
 * @version 1.0  
 */ 
package com.skymall.portal.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.common.utils.CookieUtils;
import com.skymall.common.utils.HttpClientUtil;
import com.skymall.common.utils.JsonUtils;
import com.skymall.pojo.TbItem;
import com.skymall.portal.pojo.CartItem;
import com.skymall.portal.service.CartService;

/**  
 * <p>Title: CartServiceImp</p>  
 * <p>Description: </p>  
 * @author shianliang 
 * @date 2018年5月13日  
 */
@Service
public class CartServiceImp implements CartService {

	/**rest服务基础url*/
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	/**rest服务获取商品基本信息url*/
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;
	


	@Override
	public SkymallResult addCart(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
		
		//从cookie中查询购物车是否有此商品
		//如果有，在购物车中的原商品数量+num
		List<CartItem> itemList=getCartItem(request);
		
		for (CartItem cartItem : itemList) {
			if(cartItem.getId()==itemId){
				cartItem.setNum(cartItem.getNum()+num);
				String json=JsonUtils.objectToJson(itemList);
				CookieUtils.setCookie(request, response, "TT_CART", json,true);
				return SkymallResult.ok();
			}
		}
		
		//如果没有，在购物车中添加此商品
		//通过商品id获取商品基本信息
		String itemStr=HttpClientUtil.doGet(REST_BASE_URL+ITEM_INFO_URL+itemId);
		SkymallResult result=SkymallResult.formatToPojo(itemStr, TbItem.class);

		if(result.getStatus()==200){
			CartItem item=new CartItem();
			TbItem tbItem=(TbItem) result.getData();
			item.setId(tbItem.getId());
			item.setNum(num);
			item.setPrice(tbItem.getPrice());
			item.setTitle(tbItem.getTitle());
			item.setImage(StringUtils.isBlank(tbItem.getImage())?"":tbItem.getImage().split(",")[0]);
			itemList.add(item);
		}
		
		String json=JsonUtils.objectToJson(itemList);
		
		CookieUtils.setCookie(request, response, "TT_CART", json,true);
		
		return SkymallResult.ok();
	}
	
	/**
	 * 取cookie中的购物车
	 */
	@Override
	public List<CartItem> getCartItem(HttpServletRequest request) {
		
		String json=CookieUtils.getCookieValue(request,"TT_CART",true);
		if(StringUtils.isBlank(json)){
			return new ArrayList<CartItem>();
		}
		try {
			List<CartItem> list=JsonUtils.jsonToList(json, CartItem.class);
			if(list==null){
				return new ArrayList<CartItem>();	
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<CartItem>();
		}
		
		
		
	}

	@Override
	public SkymallResult delCartItem(long itemId,HttpServletRequest request,HttpServletResponse response) {
		
		List<CartItem> cartItem = getCartItem(request);

		for (CartItem cartItem2 : cartItem) {
			if(itemId==cartItem2.getId()){
				cartItem.remove(cartItem2);
				break;
			}
		}
		String json=JsonUtils.objectToJson(cartItem);
		CookieUtils.setCookie(request, response, "TT_CART", json, true);
		return 	SkymallResult.ok();
	}


}
