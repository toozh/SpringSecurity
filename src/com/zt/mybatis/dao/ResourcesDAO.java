package com.zt.mybatis.dao;

import java.util.List;

import com.zt.model.Resources;
import com.zt.model.Roles;
import com.zt.model.RolesResources;

public interface ResourcesDAO {
	public List<Resources> getAllResources();
	
	public List<Resources> getResourcesByRoles(List<Roles> roles);
	
	public List<Resources> getResourcesByRole_id(int role_id);
	
	public void insertRolesResources(RolesResources rolesResources);

	public void deleteRolesResourcesByRoles_id(int roles_id);
}
