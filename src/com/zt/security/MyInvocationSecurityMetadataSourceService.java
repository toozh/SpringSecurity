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
 * 1.��Ҫ�������ݿ��е���Դ��Ȩ�޵Ķ�Ӧ��ϵ
 * 2.���� �����URL���� �����Ȩ�� 
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
	
	//��Ҫ�������ݿ��е���Դ��Ȩ�޵Ķ�Ӧ��ϵ
	private void loadResourceDefine() {
		  /*
		   * Ӧ������ԴΪkey�� Ȩ��Ϊvalue�� ��Դͨ��Ϊurl�� Ȩ�޾�����Щ��ROLE_Ϊǰ׺�Ľ�ɫ�� һ����Դ�����ɶ��Ȩ�������ʡ�
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

	//�����û������URL ���� �����Ȩ�޼���
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		  // object ��һ��URL�����û������url��
		  String url = ((FilterInvocation) object).getRequestUrl();
		  int firstQuestionMarkIndex = url.indexOf("?");

          if (firstQuestionMarkIndex != -1) {
        	  url = url.substring(0, firstQuestionMarkIndex);
          }

		  Iterator<String> ite = resourceMap.keySet().iterator();
		  
		  System.out.println("�û������url="+url);
		  
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
