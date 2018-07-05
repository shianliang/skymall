/**  
 * <p>Title: PageController.java</p>  
 * <p>Description: </p>   
 * @author shianliang  
 * @date 2018年5月6日  
 * @version 1.0  
 */ 
package com.skymall.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**  
 * <p>Title: PageController</p>  
 * <p>Description: </p>  
 * @author shianliang 
 * @date 2018年5月6日  
 */
@Controller
@RequestMapping("/page")
public class PageController {

	@RequestMapping("/login")
	public String login(String redirect,Model model){
		model.addAttribute("redirect",redirect);
		
		return "login";
	}
	/**
	 * 
	 * <p>Description: </p> 
	 * @author shianliang  
	 * @date 2018年5月6日  
	 * @version 1.0
	 * @return
	 */
	@RequestMapping("/register")
	public String register(){
		return "register";
	}
	
}
