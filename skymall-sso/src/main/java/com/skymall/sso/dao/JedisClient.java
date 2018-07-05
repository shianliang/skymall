package com.skymall.sso.dao;

import redis.clients.jedis.Jedis;

public interface JedisClient {

	String get(String key);
	String set(String key,String value);
	String hget(String hkey,String key);
	long hset(String hkey,String key ,String value);
	long del(String key);
	long hdel(String hkey,String key);
	
	/**返回key对应的value+1*/
	long incr(String key);
	/**设置制定key的过期时间(单位是秒)*/
	long expire(String key,int second);
	/**查看制定key的过期时间（单位是秒）*/
	long ttl(String key);
	
}
