package com.midvi.streetMapModels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ways implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<way> way = new ArrayList<>();
	public ways() {
		
	}
	public ways(List<way> ways) {
		super();
		this.way = ways;
	}
	public List<way> getWay() {
		return way;
	}
	public void setWay(List<way> way) {
		this.way = way;
	}
	@Override
	public String toString() {
		return "ways [ways=" + way + "]";
	}
	
}
