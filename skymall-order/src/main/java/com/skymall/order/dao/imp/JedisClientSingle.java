package com.skymall.order.dao.imp;

import org.springframework.beans.factory.annotation.Autowired;

import com.skymall.order.dao.JedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * jedis单机版客户端 dao
* @ClassName: JedisClientSingle 
* @Description: TODO
* @author   
* @date 2018年3月22日 下午1:46:20 
* @version V1.0
 */
public class JedisClientSingle implements JedisClient {

	@Autowired
	private JedisPool jedisPool;
	
	
	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String value = jedis.get(key);
		jedis.close();
		return value;
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis=jedisPool.getResource();
		jedis.set(key, value);
		jedis.close();
		return null;
	}

	@Override
	public String hget(String hkey, String key) {
		Jedis jedis= jedisPool.getResource();
		String str=jedis.hget(hkey, key);
		jedis.close();
		return str;
	}

	@Override
	public long hset(String hkey, String key, String value) {
		Jedis jedis= jedisPool.getResource();
		long result=jedis.hset(hkey, key,value);
		jedis.close();
		return result;
	}

	@Override
	public long incr(String key) {
		Jedis jedis= jedisPool.getResource();
		long result=jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public long expire(String key, int second) {
		Jedis jedis= jedisPool.getResource();
		long result=jedis.expire(key, second);
		jedis.close();
		return result;
	}

	@Override
	public long ttl(String key) {
		Jedis jedis= jedisPool.getResource();
		long result=jedis.ttl(key);
		jedis.close();
		return result;
	}

	@Override
	public long del(String key) {
		Jedis jedis=jedisPool.getResource();
		long result=jedis.del(key);
		jedis.close();
		return result;
	}

	@Override
	public long hdel(String hkey, String key) {
		Jedis jedis=jedisPool.getResource();
		long result=jedis.hdel(hkey,key);
		jedis.close();
		return result;
	}

}
