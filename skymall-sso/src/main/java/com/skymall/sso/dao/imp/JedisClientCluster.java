package com.skymall.sso.dao.imp;

import org.springframework.beans.factory.annotation.Autowired;

import com.skymall.sso.dao.JedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
/**
 * jedis集群客户端dao 
* @ClassName: JedisClientCluster 
* @Description: TODO
* @author 
* @date 2018年3月22日 下午1:44:54 
* @version V1.0
 */
public class JedisClientCluster implements JedisClient {

	@Autowired
	private JedisCluster jedisCluster;
	@Override
	public String get(String key) {
		
		return jedisCluster.get(key);
	}

	@Override
	public String set(String key, String value) {
		return jedisCluster.set(key, value);
	}

	@Override
	public String hget(String hkey, String key) {
		return jedisCluster.hget(hkey, key);
	}

	@Override
	public long hset(String hkey, String key, String value) {
		return jedisCluster.hset(hkey,key, value);
	}

	@Override
	public long incr(String key) {
		return jedisCluster.incr(key);
	}

	@Override
	public long expire(String key, int second) {
		return jedisCluster.expire(key, second);
	}

	@Override
	public long ttl(String key) {
		return jedisCluster.ttl(key);
	}

	@Override
	public long del(String key) {
		
		return jedisCluster.del(key);
	}

	@Override
	public long hdel(String hkey, String key) {
		
		return jedisCluster.hdel(hkey, key);
	}


}
