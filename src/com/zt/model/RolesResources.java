package com.zt.model;

import java.io.Serializable;

public class RolesResources implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int rrid;
	private int rid;
	private int rsid;
	
	public int getRrid() {
		return rrid;
	}
	public void setRrid(int rrid) {
		this.rrid = rrid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getRsid() {
		return rsid;
	}
	public void setRsid(int rsid) {
		this.rsid = rsid;
	}
}	
