package com.zt.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.zt.model.Users;

public interface IUsersService {
	
	public Users findUsersByUser_name(String user_name);
	
	public List<Users> getUsersList();
	
	public void delete(int id);
	
	public int update(Users users);
	
	public int insert(Users users);
	
	/** 
     * 根据用户名获取到用户的权限并封装成GrantedAuthority集合 
     * @param username 
     */  
    public Collection<GrantedAuthority> loadUserAuthorities(String username);
    
    public Users getByUsername(String username);
}
