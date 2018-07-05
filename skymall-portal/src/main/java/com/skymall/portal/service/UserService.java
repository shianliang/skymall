/**  
 * <p>Title: UserService.java</p>  
 * <p>Description: </p>   
 * @author shianliang  
 * @date 2018年5月7日  
 * @version 1.0  
 */ 
package com.skymall.portal.service;

import com.skymall.pojo.TbUser;

/**  
 * 用户管理service
 * <p>Title: UserService</p>  
 * <p>Description: </p>  
 * @author shianliang 
 * @date 2018年5月7日  
 */
public interface UserService {

	/**
	 * 通过token取用户信息
	 * <p>Description: </p> 
	 * @author shianliang  
	 * @date 2018年5月7日  
	 * @version 1.0
	 * @param token
	 * @return
	 */
	TbUser getUserByToken(String token);
	
}
