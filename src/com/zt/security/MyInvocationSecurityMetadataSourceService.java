package com.zt.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import com.zt.model.Resources;
import com.zt.mybatis.dao.ResourcesDAO;


/**
 * 1.主要加载数据库中的资源和权限的对应关系
 * 2.根据 请求的URL返回 所需的权限 
 * @author zhangtao
 *
 */

@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource{
	
	private ResourcesDAO resourcesDAO;
	

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	
	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	
	public MyInvocationSecurityMetadataSourceService() {
	}
	
	public MyInvocationSecurityMetadataSourceService(ResourcesDAO resourcesDAO) {
		 this.resourcesDAO = resourcesDAO;
		 loadResourceDefine();
	}
	
	//主要加载数据库中的资源和权限的对应关系
	private void loadResourceDefine() {
		  /*
		   * 应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。
		   * sparta
		   */
		  resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		  List<Resources> resourcesList = resourcesDAO.getAllResources();
		  
		  for (Resources resources : resourcesList) {
			  if (resourceMap.containsKey(resources.getUrl())) {
				  Collection<ConfigAttribute> value = resourceMap.get(resources.getUrl());
				  ConfigAttribute ca = new SecurityConfig(resources.getName());
				  value.add(ca);
				  resourceMap.put(resources.getUrl(), value);
		      } else {
		    	  Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
		    	  ConfigAttribute ca = new SecurityConfig(resources.getName());
		    	  atts.add(ca);
		    	  resourceMap.put(resources.getUrl(), atts);
			  }
		  }
	}
	
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	//根据用户请求的URL 返回 所需的权限集合
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		  // object 是一个URL，被用户请求的url。
		  String url = ((FilterInvocation) object).getRequestUrl();
		  int firstQuestionMarkIndex = url.indexOf("?");

          if (firstQuestionMarkIndex != -1) {
        	  url = url.substring(0, firstQuestionMarkIndex);
          }

		  Iterator<String> ite = resourceMap.keySet().iterator();
		  
		  System.out.println("用户请求的url="+url);
		  
		  while (ite.hasNext()) {
			  String resURL = ite.next();
			  if (antPathMatcher.match(url, resURL)) {
				  return resourceMap.get(resURL);
			  }
		  }

		  return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
