package com.zt.service;

import java.util.List;

import com.zt.model.Resources;

public interface IResourcesService {
	
	public List<Resources> getResourcesByRole_id(int role_id);
	
	public List<Resources> getAllResources();
	
	public void insertRolesResources(int roles_id,String[] resouces_ids);

	public void deleteRolesResourcesByRoles_id(String roles_id);
}
