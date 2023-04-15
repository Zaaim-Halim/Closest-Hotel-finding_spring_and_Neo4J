package com.midvi.utils;

import java.util.Map;

import com.midvi.models.Location;

public class NodeMapWrapper {
	private Map<Long,Location> nodeMap;

	public NodeMapWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NodeMapWrapper(Map<Long, Location> nodeMap) {
		super();
		this.nodeMap = nodeMap;
	}

	public Map<Long, Location> getNodeMap() {
		return nodeMap;
	}

	public void setNodeMap(Map<Long, Location> nodeMap) {
		this.nodeMap = nodeMap;
	}

}
