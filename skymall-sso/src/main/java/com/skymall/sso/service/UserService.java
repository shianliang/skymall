/**  
 * <p>Title: UserService.java</p>  
 * <p>Description: </p>   
 * @author shianliang  
 * @date 2018年5月3日  
 * @version 1.0  
 */ 
package com.skymall.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.pojo.TbUser;

/**  
 * <p>Title: UserService</p>  
 * <p>Description: </p>  
 * @author shianliang 
 * @date 2018年5月3日  
 */
public interface UserService {

	/**
	 * 
	 * <p>Description: 根据数据类型效验数据是否可用（1-username 2-phone 3-emaill）</p> 
	 * @author shianliang  
	 * @date 2018年5月3日  
	 * @version 1.0
	 * @param data
	 * @param type
	 * @return
	 */
	SkymallResult checkData(String data,Integer type);
		
	/**
	 * 
	 * <p>Description:用户注册 </p> 
	 * @author shianliang  
	 * @date 2018年5月5日  
	 * @version 1.0
	 * @param userName
	 * @param password
	 * @param phone
	 * @param email
	 * @return
	 */
	SkymallResult register(TbUser user);
	
	/**
	 * 
	 * <p>Description: 用户登陆</p> 
	 * @author shianliang  
	 * @date 2018年5月5日  
	 * @version 1.0
	 * @param userName
	 * @param password
	 * @return
	 */
	SkymallResult login(String userName ,String password,HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 
	 * <p>Description: 通过token获取user</p> 
	 * @author shianliang  
	 * @date 2018年5月6日  
	 * @version 1.0
	 * @param token
	 * @return
	 */
	SkymallResult getUserByToken(String token);
}
