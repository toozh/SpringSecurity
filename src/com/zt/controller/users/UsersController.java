package com.zt.controller.users;


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zt.service.IUsersService;


@Controller
public class UsersController {
	
	@Autowired
	private IUsersService usersService;
	
	@RequestMapping("delete.do")
	public String delete(HttpServletRequest request){
		String id = request.getParameter("id");
		usersService.delete(Integer.parseInt(id));
		
		return "redirect:login.do";
	}
}
