package com.zt.security;

import java.util.ArrayList;
import java.util.Collection;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



/**
 *该类的主要作用是为Spring Security提供一个经过用户认证后的UserDetails。
 *该UserDetails包括用户名、密码、是否可用、是否过期等信息。
 *sparta 11/3/29
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private PubUsersHome pubUsersHome;
 
	@Autowired
	private PubAuthoritiesResourcesHome pubAuthoritiesResourcesHome;
 
	@Autowired
	private DataSource dataSource;
 
	@Autowired
	private UserCache userCache;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
  
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
  
  
		//得到用户的权限
		auths = pubUsersHome.loadUserAuthoritiesByName( username );
  
		String password = null;
  
		//取得用户的密码
		password = pubUsersHome.getPasswordByUsername( username );  
   
		return new User( username, password, true, "", true, true, true, auths);
	}
  
	//set PubUsersHome
	public void setPubUsersHome( PubUsersHome pubUsersHome ){
		this.pubUsersHome = pubUsersHome;
		
	}
 
	public PubUsersHome getPubUsersHome(){
		return pubUsersHome;
	}
 
 
	//set PubAuthoritiesResourcesHome
	public void setPubAuthoritiesResourcesHome( PubAuthoritiesResourcesHome pubAuthoritiesResourcesHome ){
		this.pubAuthoritiesResourcesHome = pubAuthoritiesResourcesHome;
  
	}
 
	 public PubAuthoritiesResourcesHome getPubAuthoritiesResourcesHome(){
		 return pubAuthoritiesResourcesHome;
	  
	 }
	 
	 //set DataSource
	 public void setDataSource( DataSource dataSource ){
		 this.dataSource = dataSource;
	 }
	 
	 public DataSource getDataSource(){
		 return dataSource;
	 }
	 
	 //设置用户缓存功能。
	 public void setUserCache(UserCache userCache) {
	     this.userCache = userCache;
	 }
	    
	 public UserCache getUserCache(){
	     return this.userCache;
	 }
 
}
