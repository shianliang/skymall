/**  
 * <p>Title: OrderServiceImp.java</p>  
 * <p>Description: </p>   
 * @author shianliang  
 * @date 2018年5月20日  
 * @version 1.0  
 */ 
package com.skymall.order.service.imp;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.dao.TbOrderDao;
import com.skymall.dao.TbOrderItemDao;
import com.skymall.dao.TbOrderShippingDao;
import com.skymall.order.dao.JedisClient;
import com.skymall.order.service.OrderService;
import com.skymall.pojo.TbOrder;
import com.skymall.pojo.TbOrderItem;
import com.skymall.pojo.TbOrderShipping;

/**  
 * <p>Title: OrderServiceImp</p>  
 * <p>Description: </p>  
 * @author shianliang 
 * @date 2018年5月20日  
 */
@Service
public class OrderServiceImp implements OrderService {

	@Autowired
	private TbOrderDao orderDao;
	
	@Autowired
	private TbOrderItemDao orderItemDao;
	
	@Autowired
	private TbOrderShippingDao orderShippingDao;
	
	@Autowired
	private JedisClient jedisClient;
	
	/**利用redis生成订单号的key*/
	@Value("${ORDER_GEN_KEY}")
	private String ORDER_GEN_KEY;
	
	/**订单号初始值*/
	@Value("${ORDER_INIT_ID}")
	private String ORDER_INIT_ID;
	
	/**利用redis生成订单详情id的key*/
	@Value("${ORDER_DETATL_GEN_KEY}")
	private String ORDER_DETATL_GEN_KEY;
	
	
	
	@Override
	public SkymallResult createOrder(TbOrder order, List<TbOrderItem> orderItemList, TbOrderShipping orderShipping) {

		//插入订单
		//用redis的incr方法生成订单号
		if(StringUtils.isBlank(jedisClient.get(ORDER_GEN_KEY))){
			 jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID);
		}
		long orderId=jedisClient.incr(ORDER_GEN_KEY);
		order.setOrderId(orderId+"");
		//状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
		order.setStatus(1);
		order.setCreateTime(new Date());
		order.setUpdateTime(new Date());
		order.setBuyerRate(0);
		orderDao.insert(order);
		//循环插入订单明细
		for (TbOrderItem orderItem : orderItemList) {
			//用redis的incr方法生成订单详情id
			if(StringUtils.isBlank(jedisClient.get(ORDER_DETATL_GEN_KEY))){
				jedisClient.set(ORDER_DETATL_GEN_KEY, "100");
			}
			long orderItemId=jedisClient.incr(ORDER_DETATL_GEN_KEY);
			orderItem.setId(orderItemId+"");
			orderItem.setOrderId(orderId+"");
			orderItemDao.insert(orderItem);
		}
		//插入收货记录
		orderShipping.setOrderId(orderId+"");
		orderShipping.setCreated(new Date());
		orderShipping.setUpdated(new Date());
		orderShippingDao.insert(orderShipping);
		return SkymallResult.ok(orderId);
	}

}
