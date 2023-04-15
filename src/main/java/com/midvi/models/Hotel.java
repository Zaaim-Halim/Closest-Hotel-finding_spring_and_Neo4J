package com.midvi.models;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.neo4j.core.schema.CompositeProperty;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("Hotel")
public class Hotel {
	
	@Id
	private Long id;
	private Double lat;
	private Double lon;
	private String name;
	private String nameAR;
	@Property("address")
	private String address;
	@CompositeProperty
	private Map<String, String> infos;
	public Hotel(Long id, Double lat, Double lon, String name, String nameAR, String address,
			Map<String, String> infos) {
		super();
		this.id = id;
		this.lat = lat;
		this.lon = lon;
		this.name = name;
		this.nameAR = nameAR;
		this.address = address;
		this.infos = infos;
	}
	public Hotel() {
		infos = new HashMap<>();
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameAR() {
		return nameAR;
	}
	public void setNameAR(String nameAR) {
		this.nameAR = nameAR;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Map<String, String> getInfos() {
		return infos;
	}
	public void setInfos(Map<String, String> infos) {
		this.infos = infos;
	}
	@Override
	public String toString() {
		return "Hotel [id=" + id + ", lat=" + lat + ", lon=" + lon + ", name=" + name + ", nameAR=" + nameAR
				+ ", address=" + address + ", infos=" + infos + "]";
	}
	
}