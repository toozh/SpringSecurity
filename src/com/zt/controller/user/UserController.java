package com.zt.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zt.service.IUserService;

@Controller
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("delete.do")
	public String delete(HttpServletRequest request){
		String id = request.getParameter("id");
		
		userService.delete(Integer.parseInt(id));
		
		return null;
	}
}
