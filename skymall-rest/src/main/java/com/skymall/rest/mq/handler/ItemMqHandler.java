/**  
 * <p>Title: ItemMqHandler.java</p>  
 * <p>Description: </p>   
 * @author shianliang  
 * @date 2018年7月3日  
 * @version 1.0  
 */ 
package com.skymall.rest.mq.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.skymall.common.utils.JsonUtils;
import com.skymall.rest.dao.JedisClient;

/**  
 * <p>Title: ItemMqHandler</p>  
 * <p>Description: </p>  
 * @author shianliang 
 * @date 2018年7月3日  
 */
public class ItemMqHandler {
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${ITEM_REDIS_KEY}")
	private String ITEM_REDIS_KEY;
	
	public void execute(String msg){
		
		Map<String, Object> map=JsonUtils.jsonToPojo(msg, Map.class);
		
		String type=(String)map.get("type");
		Long itemId=(Long)map.get("itemId");
		if("delete".equals(type)){
			jedisClient.del(ITEM_REDIS_KEY+":"+itemId+":base");
			jedisClient.del(ITEM_REDIS_KEY+":"+itemId+":desc");
			jedisClient.del(ITEM_REDIS_KEY+":"+itemId+":param");
		}
	}
}
