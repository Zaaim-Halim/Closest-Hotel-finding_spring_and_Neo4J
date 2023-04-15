package com.midvi.streetMapModels;

import java.io.Serializable;


public class member implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String type;
	private Long ref;
	private String role;
	public member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public member(String type, Long ref, String role) {
		super();
		this.type = type;
		this.ref = ref;
		this.role = role;
	}
	
	public String getType() {
		return type;
	}
   void setType(String type) {
		this.type = type;
	}

	public Long getRef() {
		return ref;
	}

	public void setRef(Long ref) {
		this.ref = ref;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

}
