package com.midvi.utils;

import java.util.Map;

import com.midvi.models.Hotel;

public class HotelsMapWrraper {
	private Map<Long,Hotel> hotelsMap;

	public Map<Long, Hotel> getHotelsMap() {
		return hotelsMap;
	}

	public void setHotelsMap(Map<Long, Hotel> hotelsMap) {
		this.hotelsMap = hotelsMap;
	}

	public HotelsMapWrraper(Map<Long, Hotel> hotelsMap) {
		super();
		this.hotelsMap = hotelsMap;
	}

	public HotelsMapWrraper() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
