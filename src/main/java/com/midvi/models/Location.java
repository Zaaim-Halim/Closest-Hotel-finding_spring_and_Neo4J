package com.midvi.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
@Node("Node")
public class Location {
	
	@Id
	private Long id;
	private Double lat;
	private Double lon;
	
	public Location() {
		super();
	}

	public Location(Long id, Double lat, Double lon) {
		super();
		this.id = id;
		this.lat = lat;
		this.lon = lon;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Location [id=" + id + ", lat=" + lat + ", lon=" + lon + "]";
	}

	


}
