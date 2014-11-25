package com.zt.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

/**
 * ��Ҫ����֤�û��Ƿ���Ȩ�� ���� �������Դ 
 * @author zhangtao
 *
 */

@Service
public class MyAccessDecisionManager implements AccessDecisionManager {
	 
	 public void decide( Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) 
			 throws AccessDeniedException, InsufficientAuthenticationException{
	  
		  if( configAttributes == null ) {
			  return;
		  }
		  
		  Iterator<ConfigAttribute> ite = configAttributes.iterator();
		  
		  while( ite.hasNext()){
		   
			  ConfigAttribute ca = ite.next();
			  String needRole = ((SecurityConfig)ca).getAttribute();
			  //ga Ϊ�û����������Ȩ�ޡ� needRole Ϊ������Ӧ����ԴӦ�þ��е�Ȩ�ޡ�
			  for( GrantedAuthority ga: authentication.getAuthorities()){
				  if(needRole.trim().equals(ga.getAuthority().trim())){
 					  return;
				  }
			  }
	   
		  }
	  
		  throw new AccessDeniedException("no right");
	 }
	 
	 public boolean supports( ConfigAttribute attribute ){
		 return true;
	 }
	 
	 public boolean supports(Class<?> clazz){
		 return true;
	 }
	 

	}
