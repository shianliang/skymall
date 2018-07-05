/**  
 * <p>Title: UserServiceImpl.java</p>  
 * <p>Description: </p>   
 * @author shianliang  
 * @date 2018年5月3日  
 * @version 1.0  
 */
package com.skymall.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.common.utils.CookieUtils;
import com.skymall.common.utils.JsonUtils;
import com.skymall.dao.TbUserDao;
import com.skymall.pojo.TbUser;
import com.skymall.pojo.TbUserQuery;
import com.skymall.pojo.TbUserQuery.Criteria;
import com.skymall.sso.dao.JedisClient;
import com.skymall.sso.service.UserService;

/**
 * <p>
 * Title: UserServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author shianliang
 * @date 2018年5月3日
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserDao userDao;

	@Autowired
	private JedisClient jedisClient;
	
	/**自定义session，存入redis的hkey*/
	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;
	
	/**session的过期时间*/
	@Value("${SSO_SESSION_EXPIRE}")
	private Integer SSO_SESSION_EXPIRE;
	
	@Override
	public SkymallResult checkData(String data,Integer type) {

		TbUserQuery query = new TbUserQuery();
		Criteria criteria = query.createCriteria();
		switch (type) {
		case 1:
			criteria.andUsernameEqualTo(data);
			break;
		case 2:
			criteria.andPhoneEqualTo(data);
			break;
		case 3:
			criteria.andEmailEqualTo(data);
			break;
		default:
			return SkymallResult.build(400, "检查类型错误");
		}
		List<TbUser> user = userDao.selectByExample(query);

		if (user == null || user.size() == 0) {
			return SkymallResult.ok(true);
		}

		return SkymallResult.ok(false);
	}


	@Override
	public SkymallResult register(TbUser user) {
		user.setCreated(new Date());
		user.setUpdated(new Date());
		//密码md5加密
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
			userDao.insert(user);
		return SkymallResult.ok();
	}

	@Override
	public SkymallResult login(String userName, String password ,HttpServletRequest request,HttpServletResponse response) {
		
	
		TbUserQuery query=new TbUserQuery();
		Criteria criteria=query.createCriteria();
		criteria.andUsernameEqualTo(userName);
		List<TbUser> list=userDao.selectByExample(query);
	
		if(list==null||list.size()<1){
			return SkymallResult.build(400, "用户名不存在");
		}
		TbUser user= list.get(0);
		if(!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
			return SkymallResult.build(400, "密码错误");
		}
		//生成uuid作为sessionid
		String token = UUID.randomUUID().toString();
		
		//将user对象转为json
		String json=JsonUtils.objectToJson(user);
		
		//uuid作为key   json格式user对象作为值存到redis
		jedisClient.set(REDIS_USER_SESSION_KEY+":"+token, json);
		
		//设置过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY+":"+token, SSO_SESSION_EXPIRE);
		
		//将token设置到cookie中
		CookieUtils.setCookie(request, response, "TT_TOKEN", token);

		
		return SkymallResult.ok(token);
	}



	@Override
	public SkymallResult getUserByToken(String token) {
		
		String userJson=jedisClient.get(REDIS_USER_SESSION_KEY+":"+token);
		
		if(userJson==null){
			return SkymallResult.build(400,"登陆已超时,请重新登录");
		}
		//重新设置过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY+":"+token, SSO_SESSION_EXPIRE);
		TbUser user=JsonUtils.jsonToPojo(userJson, TbUser.class);
		return SkymallResult.ok(user);
	}



}
