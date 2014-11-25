package com.zt.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zt.model.Users;
import com.zt.service.IUsersService;

/**
 * 用户登录验证
 * @author zhangtao
 *
 */

@Service
public class DefaultUserDetailsService implements UserDetailsService{

	@Autowired
	private IUsersService usersService;
//	@Autowired  
//	private UserCache userCache;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();  
//		Users user = (Users) userCache.getUserFromCache(username);  
		Users user = null;
        if(user == null){
        	user = usersService.getByUsername(username);
        	if(user == null){
        		throw new UsernameNotFoundException("用户"+username+"不存在");
        	}
        	//得到用户的权限  
        	auths = usersService.loadUserAuthorities(username);
        	user.setAuthorities(auths);  
        	user.setAccountNonExpired(true);
        	user.setAccountNonLocked(true);
        	user.setCredentialsNonExpired(true);
        	user.setEnabled(true);
        }
        
        return user;
	}

}
