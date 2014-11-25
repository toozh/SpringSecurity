package com.zt.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Users implements UserDetails,Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String user_name;
	private String user_pwd;
	private String insert_time;
	private String update_time;
	private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	private Set<UsersRoles> usersRoleses = new HashSet<UsersRoles>(0);
    private Collection<GrantedAuthority>  authorities;
	
	public Set<UsersRoles> getUsersRoleses() {
		return usersRoleses;
	}
	public void setUsersRoleses(Set<UsersRoles> usersRoleses) {
		this.usersRoleses = usersRoleses;
	}
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInsert_time() {
		return insert_time;
	}
	public void setInsert_time(String insert_time) {
		this.insert_time = insert_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getPassword() {
		return this.user_pwd;
	}
	public String getUsername() {
		return this.user_name;
	}
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}
	public boolean isEnabled() {
		return this.enabled;
	}
}
