package com.midvi.streetMapModels;

import java.io.Serializable;

public class nd implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long ref;

	public nd(Long ref) {
		super();
		this.ref = ref;
	}
	public Long getRef() {
		return ref;
	}
	public void setRef(Long ref) {
		this.ref = ref;
	}

	public nd() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "nd [ref=" + ref + "]";
	}
	

}
