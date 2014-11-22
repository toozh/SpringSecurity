package com.zt.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;


/**
 * ����ĵĵط��������ṩĳ����Դ��Ӧ��Ȩ�޶��壬��getAttributes�������صĽ���� �����ڳ�ʼ��ʱ��Ӧ��ȡ��������Դ�����Ӧ��ɫ�Ķ��塣
 * 
 */
@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

	 @Autowired
	 private PubAuthoritiesResourcesHome pubAuthoritiesResourcesHome;
	
	 private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	
	 private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	 public MyInvocationSecurityMetadataSourceService() {
		 loadResourceDefine();
	 }

	 private void loadResourceDefine() {
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		 Session session = sessionFactory.openSession();

		 String username = "";
		 String sql = "";

		 // ��Web����������ʱ����ȡϵͳ�е�����Ȩ�ޡ�
		 sql = "select authority_name from pub_authorities";

		 List<String> query = session.createSQLQuery(sql).list();

		  /*
		   * Ӧ������ԴΪkey�� Ȩ��Ϊvalue�� ��Դͨ��Ϊurl�� Ȩ�޾�����Щ��ROLE_Ϊǰ׺�Ľ�ɫ�� һ����Դ�����ɶ��Ȩ�������ʡ�
		   * sparta
		   */
		 resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

		 for (String auth : query) {
			 ConfigAttribute ca = new SecurityConfig(auth);

			 List<String> query1 = session
					 .createSQLQuery(
							 "select b.resource_string "
							  + "from Pub_Authorities_Resources a, Pub_Resources b, "
							  + "Pub_authorities c where a.resource_id = b.resource_id "
							  + "and a.authority_id=c.authority_id and c.Authority_name='"
							  + auth + "'").list();

			 for (String res : query1) {
				 String url = res;
    
			    /*
			     * �ж���Դ�ļ���Ȩ�޵Ķ�Ӧ��ϵ������Ѿ�������ص���Դurl����Ҫͨ����urlΪkey��ȡ��Ȩ�޼��ϣ���Ȩ�����ӵ�Ȩ�޼����С�
			     * sparta
			     */
				 if (resourceMap.containsKey(url)) {

					 Collection<ConfigAttribute> value = resourceMap.get(url);
					 value.add(ca);
					 resourceMap.put(url, value);
				 } else {
					 Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
					 atts.add(ca);
					 resourceMap.put(url, atts);
				 }

			 }

		 }

	 }

	 @Override
	 public Collection<ConfigAttribute> getAllConfigAttributes() {
		 return null;
	 }

	 // ����URL���ҵ���ص�Ȩ�����á�
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

		 while (ite.hasNext()) {
			 String resURL = ite.next();
   
			 if (urlMatcher.pathMatchesUrl(url, resURL)) {

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
