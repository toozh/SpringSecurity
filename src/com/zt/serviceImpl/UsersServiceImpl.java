package com.zt.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.zt.model.Resources;
import com.zt.model.Users;
import com.zt.mybatis.dao.UsersDAO;
import com.zt.service.IUsersService;

@Service
public class UsersServiceImpl implements IUsersService {

	@Autowired
	private UsersDAO usersDAO;
	
	public Users findUsersByUser_name(String user_name) {
		return usersDAO.findUsersByUser_name(user_name);
	}

	@Override
	public List<Users> getUsersList() {
		return usersDAO.getUsersList();
	}

	@Override
	public void delete(int id) {
		usersDAO.delete(id);
	}

	@Override
	public Collection<GrantedAuthority> loadUserAuthorities(String username) {
		List<Resources> list = usersDAO.getResourcesByUsername(username);
        
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
          
        for (Resources resources : list) {  
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(resources.getName());
            auths.add(grantedAuthority);  
        }  
  
        return auths;  
	}

	@Override
	public Users getByUsername(String username) {
		return usersDAO.getByUsername(username);
	}

	@Override
	public int update(Users users) {
		return usersDAO.update(users);
	}

	@Override
	public int insert(Users users) {
		return usersDAO.insert(users);
	}

}
