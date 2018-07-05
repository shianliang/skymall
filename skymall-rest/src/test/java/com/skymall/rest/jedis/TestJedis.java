package com.skymall.rest.jedis;

import java.util.HashSet;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class TestJedis {

	//测试jedis
//	@Test
//	public void testJedis(){
//		//创建jedis对象
//		Jedis jedis=new Jedis("192.168.203.137",6379);
//		//与reids指令操作一至
//		jedis.set("key1","88888888888888" );
//		//删除
//		Long del = jedis.del("cccc");
//		//添加
////		String result=jedis.get("cccc");
//		System.err.println(del);
//		//关闭jedis对象
//		jedis.close();
//	}
	
	// 测试jedis连接池
//	@Test
//	public void testJedisPool(){
//		//创建连接池
//		JedisPool jedisPool=new JedisPool("192.168.203.137",6379);
//		//从连接池里取jedis对象
//		Jedis jedis = jedisPool.getResource();
//		//一下操作都一样
//		String result=jedis.get("ddd");
//		System.err.println(result);
//	    //关闭jedis
//		jedis.close();
//		//关闭连接池
//		jedisPool.close();
//	}
//	
//	
//	//测试redis集群（自带连接池）不需要关闭否则会报错
//	@Test
//	public void testJedisCluster(){
//		HashSet<HostAndPort> nodes=new HashSet<>();
//		nodes.add(new HostAndPort("192.168.203.137", 7001));
//		nodes.add(new HostAndPort("192.168.203.137", 7002));
//		nodes.add(new HostAndPort("192.168.203.137", 7003));
//		nodes.add(new HostAndPort("192.168.203.137", 7004));
//		nodes.add(new HostAndPort("192.168.203.137", 7005));
//		nodes.add(new HostAndPort("192.168.203.137", 7006));
//		JedisCluster cluster=new JedisCluster(nodes);
//		cluster.set("key2","成功了");
//		System.out.println(cluster.get("key2"));
//	
//		
//	}
//	//测试单机版jedis与spring整合
//	@Test
//	public void testJedisAndSpring(){
//		
//		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
//		JedisPool jedisPool=(JedisPool) applicationContext.getBean("redisClient");
//		Jedis jedis=jedisPool.getResource();
//		jedis.set("gggg", "09090900");
//		String str=jedis.get("gggg");
//		System.out.println(str);
//		
//		jedis.close();
//		jedisPool.close();
//	}
//	
//	//测试jedis集群与spring整合
//	@Test
//	public void JedisClusterAndSpring(){
//		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
//		JedisCluster jedisCluster=(JedisCluster) applicationContext.getBean("redisClient");
//		jedisCluster.set("name","8822288");
//		String str=jedisCluster.get("name");
//		System.out.println(str);
//	
//		
//	}
}
