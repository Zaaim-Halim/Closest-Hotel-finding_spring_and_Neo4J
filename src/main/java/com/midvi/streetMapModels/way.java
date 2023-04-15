package com.midvi.streetMapModels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class way implements Serializable{

	private static final long serialVersionUID = 5650680805478562765L;
	private Long id;
	//private List<tag> tag = new ArrayList<>();
	private List<nd> nd = new ArrayList<>();
	public way() {
		super();
		// TODO Auto-generated constructor stub
	}
	public way(Long id/*, List<com.midvi.streetMapModels.tag> tag*/, List<com.midvi.streetMapModels.nd> nd) {
		super();
		this.id = id;
		//this.tag = tag;
		this.nd = nd;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * public List<tag> getTag() { return tag; } public void setTag(List<tag> tag) {
	 * this.tag = tag; }
	 */
	public List<nd> getNd() {
		return nd;
	}
	public void setNd(List<nd> nd) {
		this.nd = nd;
	}
	@Override
	public String toString() {
		return "way [id=" + id + ", nd=" + nd + "]";
	}
	
}
