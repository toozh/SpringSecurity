package com.zt.controller.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zt.model.Users;
import com.zt.service.IUsersService;

@Controller
public class LoginController {
	
	@Autowired
	private IUsersService usersService;
	
	@RequestMapping("login.do")
	public String login(HttpServletRequest request,ModelMap modelMap){
		List<Users> usersList = usersService.getUsersList();
		modelMap.addAttribute("usersList", usersList);
		return "welcome";
	}
	
}
