package com.zt.controller.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zt.model.User;
import com.zt.service.IUserService;

@Controller
public class LoginController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("login.do")
	public String login(User loginUser,HttpServletRequest request){
		
		User realUser = userService.findUserByUser_name(loginUser.getUser_name());
		
		if(realUser !=null){
			if(loginUser.getUser_pwd().endsWith(realUser.getUser_pwd())){
				request.getSession().setAttribute("USER",realUser);
				return "welcome";
			}
		}
		
		
		return "index";
	}
	
}
