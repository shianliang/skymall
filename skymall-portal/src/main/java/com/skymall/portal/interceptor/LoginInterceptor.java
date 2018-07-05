/**  
 * <p>Title: LoginInterceptor.java</p>  
 * <p>Description: </p>   
 * @author shianliang  
 * @date 2018年5月7日  
 * @version 1.0  
 */ 
package com.skymall.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.skymall.common.utils.CookieUtils;
import com.skymall.pojo.TbUser;
import com.skymall.portal.service.UserService;
import com.skymall.portal.service.imp.UserServiceImpl;

/** 
 * 登陆拦截（拦截未登录用户） 
 * <p>Title: LoginInterceptor</p>  
 * <p>Description: </p>  
 * @author shianliang 
 * @date 2018年5月7日  
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserServiceImpl userService;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//handler执行之前
        //1.从cookie中取token
		String token=CookieUtils.getCookieValue(request, "TT_TOKEN");
	
		//2.通过token调用sso服务取用户信息
		TbUser user=userService.getUserByToken(token);
		if(user==null){
			//3.sso服务返回user信息是否存在 
			//不存在返回false，并重定向单点系统的登陆页面，且获取请求地址拼接到重定向url后（?redirect=）
			response.sendRedirect(userService.SSO_BASE_URL+userService.USR_LOGIN+"?redirect="+request.getRequestURL());
			return false;
		}
		//将用户信息放入request对象中供此次请求的所有方法使用
		request.setAttribute("user",user);
		
		//存在返回 true 
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		//handler执行之后，modelAndview执行之前
		
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//modelAndView执行之后
		
	}

}
