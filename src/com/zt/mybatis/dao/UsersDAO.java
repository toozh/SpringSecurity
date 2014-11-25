package com.zt.mybatis.dao;

import java.util.List;

import com.zt.model.Resources;
import com.zt.model.Roles;
import com.zt.model.Users;

public interface UsersDAO {
	
	public Users findUsersByUser_name(String user_name);
	
	public List<Users> getUsersList();
	
	public void delete(int id);
	
	public List<Roles> getRolesByUserId(Integer id);
	
	public List<Resources> getResourcesByUsername(String username);
	
	public Users getByUsername(String username);

	public int insert(Users users);

	public int update(Users users);
}
