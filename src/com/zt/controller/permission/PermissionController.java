package com.zt.controller.permission;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zt.model.Resources;
import com.zt.model.Roles;
import com.zt.service.IResourcesService;
import com.zt.service.IRolesService;

@Controller
public class PermissionController {

	@Autowired
	private IRolesService rolesService;
	@Autowired
	private IResourcesService resourcesService;
	
	
	@RequestMapping("/showRolesResources.do")
	public String showRolesResources(ModelMap modelMap){
		
		//1.获取全部资源权限
		List<Resources> resourcesList = resourcesService.getAllResources();
		//2.绑定角色已有的权限
		List<Roles> rolesList = rolesService.getList();
		for (Roles roles : rolesList) {
			List<Resources> list = resourcesService.getResourcesByRole_id(roles.getId());
			roles.setResourcesList(list);
		}
		
		modelMap.addAttribute("resourcesList", resourcesList)
				.addAttribute("rolesList", rolesList);
		
		return "rolesResourcesList";
	}
	
	@RequestMapping("/updatePermission.do")
	public String updatePermission(HttpServletRequest request){
		
		String roles_id = request.getParameter("roles_id");
		String[] resources = request.getParameterValues("resources");
		
		//1.先删除原先的权限
		resourcesService.deleteRolesResourcesByRoles_id(roles_id);
		
		resourcesService.insertRolesResources(Integer.parseInt(roles_id), resources);
		
		return "redirect:showRolesResources.do";
	}
	
	
}
