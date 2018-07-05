package com.skymall.rest.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.rest.dao.JedisClient;
import com.skymall.rest.service.RedisService;
@Service
public class RedisServiceImp implements RedisService {

	@Autowired
	private JedisClient jedisClient;
	
	/**resource.properties中定义的首页内容 key*/
	@Value("{INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	/**
	 *  缓存同步根据内容分类id
	 */
	@Override
	public SkymallResult contentSync(long contentCid) {
		try {
			jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentCid+"");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SkymallResult.ok();
	}

}
