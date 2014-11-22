/*
 * @(#) CustomUserDetails.java  2011-4-13 ����01:44:14
 *
 * Copyright 2011 by Sparta 
 */

package com.zt.security;

import java.util.Set;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *ʵ����UserDetails����չ������Ϣ������getSubSystem()������ sparta 11/4/13��
 */
public interface MyUserDetails extends UserDetails {

	//�û�id
	public String getUserId();

	//�û��˻�
	public String getUserAccount();

	//�û���
	public String getUserName();

	//�û�����
	public String getUserPassword();

	//�û���������
	public String getUserDesc();

	//�û��Ƿ�����
	public boolean getEnabled();

	//�Ƿ񳬼��û�
	public Boolean getIssys();
	
	//�����ĵ�λ
	public String getUserDept();

	//�û�ְλ
	public String getUserDuty();

	//�û��ֹܵ���ϵͳ
	public String getSubSystem();
	
	//�û����Ӧ�Ľ�ɫ��
	@SuppressWarnings("rawtypes")
	public Set getSysUsersRoleses();

}
