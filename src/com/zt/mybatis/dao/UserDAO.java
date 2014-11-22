package com.zt.mybatis.dao;

import java.util.List;

import com.zt.model.User;

public interface UserDAO {
	
	public User findUserByUser_name(String user_name);
	
	public List<User> getUserList();
	
	public void delete(int id);
}
