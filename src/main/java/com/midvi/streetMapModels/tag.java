package com.midvi.streetMapModels;

import java.io.Serializable;

public class tag implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String k;
	private String v;
	
	public tag(String k, String v) {
		super();
		this.k = k;
		this.v = v;
	}
	public tag() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getK() {
		return k;
	}
	public void setK(String k) {
		this.k = k;
	}

	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	@Override
	public String toString() {
		return "tag [k=" + k + ", v=" + v + "]";
	}
	
	
	

}
