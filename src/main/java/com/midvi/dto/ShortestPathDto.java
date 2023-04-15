package com.midvi.dto;

import java.util.ArrayList;
import java.util.List;

import com.midvi.models.Hotel;
import com.midvi.models.Location;

public class ShortestPathDto {
      private Double totalCost;
      private Location source;
      private Hotel destination;
      private List<Location> path;
	public ShortestPathDto(Double totalCost, Location source, Hotel destination, List<Location> path) {
		super();
		this.totalCost = totalCost;
		this.source = source;
		this.destination = destination;
		this.path = path;
	}
	public ShortestPathDto() {
		this.path = new ArrayList<Location>();
	}
	public Double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	public Location getSource() {
		return source;
	}
	public void setSource(Location source) {
		this.source = source;
	}
	public Hotel getDestination() {
		return destination;
	}
	public void setDestination(Hotel destination) {
		this.destination = destination;
	}
	public List<Location> getPath() {
		return path;
	}
	public void setPath(List<Location> path) {
		this.path = path;
	} 
}
