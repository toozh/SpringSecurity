package com.zt.model;

import java.io.Serializable;

public class UsersRoles implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int urId;
	private int uid;
	private int rid;
	
	public int getUrId() {
		return urId;
	}
	public void setUrId(int urId) {
		this.urId = urId;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
}
