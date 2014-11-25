package com.zt.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zt.model.Resources;
import com.zt.model.RolesResources;
import com.zt.mybatis.dao.ResourcesDAO;
import com.zt.service.IResourcesService;

@Service
public class ResourcesService implements IResourcesService{

	
	@Autowired
	private ResourcesDAO resourcesDAO;
	
	@Override
	public List<Resources> getResourcesByRole_id(int role_id) {
		return resourcesDAO.getResourcesByRole_id(role_id);
	}

	@Override
	public List<Resources> getAllResources() {
		return resourcesDAO.getAllResources();
	}

	@Override
	public void insertRolesResources(int roles_id, String[] resouces_ids) {
		RolesResources rolesResources = new RolesResources();
		rolesResources.setRid(roles_id);
		
		for (String resouces_id : resouces_ids) {
			rolesResources.setRsid(Integer.parseInt(resouces_id));
			resourcesDAO.insertRolesResources(rolesResources);
		}
		
	}

	@Override
	public void deleteRolesResourcesByRoles_id(String roles_id) {
		resourcesDAO.deleteRolesResourcesByRoles_id(Integer.parseInt(roles_id));
	}

}
