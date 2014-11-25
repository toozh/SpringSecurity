package com.zt.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zt.model.Roles;
import com.zt.mybatis.dao.RolesDAO;
import com.zt.service.IRolesService;

@Service
public class RolesServiceImpl implements IRolesService{
	
	@Autowired
	private RolesDAO rolesDAO;
	
	@Override
	public List<Roles> getList() {
		return rolesDAO.getList();
	}

}
