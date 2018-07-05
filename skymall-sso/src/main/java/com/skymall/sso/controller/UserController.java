/**  
 * <p>Title: UserController.java</p>  
 * <p>Description: </p>   
 * @author shianliang  
 * @date 2018年5月3日  
 * @version 1.0  
 */
package com.skymall.sso.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.skymall.common.pojo.SkymallResult;
import com.skymall.common.utils.ExceptionUtil;
import com.skymall.common.utils.RegexUtils;
import com.skymall.pojo.TbUser;
import com.skymall.sso.dao.JedisClient;
import com.skymall.sso.service.UserService;

/**
 * <p>
 * Title: UserController
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author shianliang
 * @date 2018年5月3日
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;


	
	/**
	 * 
	 * <p>Description:用户注册参数验证 </p> 
	 * @author shianliang  
	 * @date 2018年5月6日  
	 * @version 1.0
	 * @param data
	 * @param type
	 * @param callback
	 * @return
	 */
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object checkData(@PathVariable("param") String data, @PathVariable Integer type, String callback) {

		SkymallResult result = userService.checkData(data, type);

		if (StringUtils.isNotBlank(callback)) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		} else {
			return result;
		}

	}

	/**
	 * 
	 * <p>Description:用户注册 </p> 
	 * @author shianliang  
	 * @date 2018年5月6日  
	 * @version 1.0
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public SkymallResult register(TbUser user) {
		if (StringUtils.isNotBlank(user.getEmail()) && !RegexUtils.checkEmail(user.getEmail())) {

			return SkymallResult.build(400, "注册失败！邮箱的格式有误");

		} else if (StringUtils.isBlank(user.getPassword()) || user.getPassword().length() < 6) {

			// 后期密码根据规则进行校验
			return SkymallResult.build(400, "注册失败！密码为空或密码格式不对");

		} else if (StringUtils.isBlank(user.getPhone()) || !RegexUtils.checkMobile(user.getPhone())) {

			return SkymallResult.build(400, "注册失败！手机号为空或手机号格式有误");

		}
		return userService.register(user);
	}

	/**
	 * 
	 * <p>Description:用户登陆 </p> 
	 * @author shianliang  
	 * @date 2018年5月6日  
	 * @version 1.0
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Object login(String username,String password,HttpServletRequest request,HttpServletResponse response){
	
		if(StringUtils.isBlank(username)){
			return SkymallResult.build(400, "用户名不能为空");
		}else if(StringUtils.isBlank(password)){
			return SkymallResult.build(400, "密码不能为空");
		}
		
		SkymallResult result=userService.login(username, password,request,response);
		
		
		return result;
	}
	
	/**
	 * 
	 * <p>Description: 通过token获取user</p> 
	 * @author shianliang  
	 * @date 2018年5月6日  
	 * @version 1.0
	 * @param token
	 * @param callback
	 * @return
	 */
	@RequestMapping("/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token,String callback) {
		
		try {
			SkymallResult result=userService.getUserByToken(token);
			//判断是否是jsonp调用
			if(StringUtils.isNotBlank(callback)){
				MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(result);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return SkymallResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
	
	}
}
