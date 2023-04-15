package com.midvi.streetMapModels;

import java.io.Serializable;
import java.util.List;

public class relations implements Serializable{

	private static final long serialVersionUID = 1L;
    
	private List<relation> relation;

	public relations(List<relation> relation) {
		super();
		this.relation = relation;
	}

	public relations() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<relation> getRelation() {
		return relation;
	}

	public void setRelation(List<relation> relation) {
		this.relation = relation;
	}

	@Override
	public String toString() {
		return "[relations=" + relation + "]";
	}
	
}
