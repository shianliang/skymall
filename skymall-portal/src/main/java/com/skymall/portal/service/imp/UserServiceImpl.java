/**  
 * <p>Title: UserServiceImpl.java</p>  
 * <p>Description: </p>   
 * @author shianliang  
 * @date 2018年5月7日  
 * @version 1.0  
 */ 
package com.skymall.portal.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.common.utils.HttpClientUtil;
import com.skymall.pojo.TbUser;
import com.skymall.portal.service.UserService;

/**  
 * <p>Title: UserServiceImpl</p>  
 * <p>Description: </p>  
 * @author shianliang 
 * @date 2018年5月7日  
 */
@Service
public class UserServiceImpl implements UserService {

	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	
	@Value("${SSO_BASE_DOMAIN}")
	public String SSO_BASE_DOMAIN;
	
	@Value("${USER_TOKEN}")
	private String USER_TOKEN;
	/**拦截器中用到的单点登录系统的登陆地址*/
	@Value("${USR_LOGIN}")
	public String USR_LOGIN;
	
	@Override
	public TbUser getUserByToken(String token) {
		try {
			//通过token调用sso服务取用户信息
			String json=HttpClientUtil.doGet(SSO_BASE_URL+USER_TOKEN+token);
			SkymallResult result=SkymallResult.formatToPojo(json, TbUser.class);
			if(null!=result){
				TbUser user=(TbUser) result.getData();
				return user;
			}
			
		} catch (Exception e) {
				e.printStackTrace();
		}
		return null;
	}

}
