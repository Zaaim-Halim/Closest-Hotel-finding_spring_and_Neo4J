package com.midvi.streetMapModels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class nodes implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<node> node = new ArrayList<>();

	public nodes() {
		super();
	}
	public nodes(List<node> node) {
		super();
		this.node = node;
	}
	public List<node> getNodes() {
		return node;
	}
	public void setNode(List<node> node) {
		this.node = node;
	}
	@Override
	public String toString() {
		return "nodeList [nodes=" + node + "]";
	}
	

	
}
