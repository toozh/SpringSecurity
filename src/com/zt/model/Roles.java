package com.zt.model;

import java.io.Serializable;
import java.util.List;

public class Roles implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private int enable;
	
	private List<Resources> resourcesList;
	
	public List<Resources> getResourcesList() {
		return resourcesList;
	}
	public void setResourcesList(List<Resources> resourcesList) {
		this.resourcesList = resourcesList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}

}
