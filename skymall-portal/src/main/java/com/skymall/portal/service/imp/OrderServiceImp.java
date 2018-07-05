/**  
 * <p>Title: OrderServiceImp.java</p>  
 * <p>Description: </p>   
 * @author shianliang  
 * @date 2018年5月20日  
 * @version 1.0  
 */ 
package com.skymall.portal.service.imp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.common.utils.HttpClientUtil;
import com.skymall.common.utils.JsonUtils;
import com.skymall.portal.pojo.Order;
import com.skymall.portal.service.OrderService;

/**  
 * <p>Title: OrderServiceImp</p>  
 * <p>Description: </p>  
 * @author shianliang 
 * @date 2018年5月20日  
 */
@Service
public class OrderServiceImp implements OrderService {
	
	//#订单服务基础url
	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	
	//#创建订单url
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;
	
	@Override
	public String createOrder(Order order) {
 	
		String json= HttpClientUtil.doPostJson(ORDER_BASE_URL+ORDER_CREATE_URL, JsonUtils.objectToJson(order));
		
		SkymallResult result=SkymallResult.format(json);
		
		if(result.getStatus()==200){
			Object orderId=Long.parseLong(result.getData()+"");
			return orderId.toString();
		}
		
		return "";
	}

}
