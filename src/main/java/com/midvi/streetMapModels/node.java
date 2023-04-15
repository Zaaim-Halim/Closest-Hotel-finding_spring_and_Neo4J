package com.midvi.streetMapModels;

import java.io.Serializable;
import java.util.List;

public class node implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long uid;
	private Double lat;
	private Double lon;
	private List<tag> tag; // commented later
	public node(Long id, Long uid, Double lat, Double lon, List<tag> tag) {
		super();
		this.id = id;
		this.uid = uid;
		this.lat = lat;
		this.lon = lon;
	    this.tag = tag;
	}

	public node() {
		super();
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUid() {
		return uid;
	}
	
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLon() {
		return lon;
	}
	
	public void setLon(Double lon) {
		this.lon = lon;
	}
    
	
	
	  public List<tag> getTag() { return tag; }
	  
	  public void setTag(List<tag> tag) { this.tag = tag; }
	 
	 

	
}
