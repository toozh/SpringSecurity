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
 *�������Ҫ������ΪSpring Security�ṩһ�������û���֤���UserDetails��
 *��UserDetails�����û��������롢�Ƿ���á��Ƿ���ڵ���Ϣ��
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
  
  
		//�õ��û���Ȩ��
		auths = pubUsersHome.loadUserAuthoritiesByName( username );
  
		String password = null;
  
		//ȡ���û�������
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
	 
	 //�����û����湦�ܡ�
	 public void setUserCache(UserCache userCache) {
	     this.userCache = userCache;
	 }
	    
	 public UserCache getUserCache(){
	     return this.userCache;
	 }
 
}
