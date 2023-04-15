package com.midvi.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class GrapheResultDto {
	@Id
	private Long sourceId;
	private Long targetId;
	private Double totalCost;
	
	private List<Long> nodeIds;
	
	private List<Double> costs;
	public GrapheResultDto(Long sourceId, Long targetId, Double totalCost, List<Long> nodeIds, List<Double> costs) {
		super();
		this.sourceId = sourceId;
		this.targetId = targetId;
		this.totalCost = totalCost;
		this.nodeIds = nodeIds;
		this.costs = costs;
	}
	public GrapheResultDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getSourceId() {
		return sourceId;
	}
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}
	public Long getTargetId() {
		return targetId;
	}
	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}
	public Double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	public List<Long> getNodeIds() {
		return nodeIds;
	}
	public void setNodeIds(List<Long> nodeIds) {
		this.nodeIds = nodeIds;
	}
	public List<Double> getCosts() {
		return costs;
	}
	public void setCosts(List<Double> costs) {
		this.costs = costs;
	}
	@Override
	public String toString() {
		return "GrapheResultDto [sourceId=" + sourceId + ", targetId=" + targetId + ", totalCost=" + totalCost
				+ ", nodeIds=" + nodeIds + ", costs=" + costs + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((costs == null) ? 0 : costs.hashCode());
		result = prime * result + ((nodeIds == null) ? 0 : nodeIds.hashCode());
		result = prime * result + ((sourceId == null) ? 0 : sourceId.hashCode());
		result = prime * result + ((targetId == null) ? 0 : targetId.hashCode());
		result = prime * result + ((totalCost == null) ? 0 : totalCost.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrapheResultDto other = (GrapheResultDto) obj;
		if (costs == null) {
			if (other.costs != null)
				return false;
		} else if (!costs.equals(other.costs))
			return false;
		if (nodeIds == null) {
			if (other.nodeIds != null)
				return false;
		} else if (!nodeIds.equals(other.nodeIds))
			return false;
		if (sourceId == null) {
			if (other.sourceId != null)
				return false;
		} else if (!sourceId.equals(other.sourceId))
			return false;
		if (targetId == null) {
			if (other.targetId != null)
				return false;
		} else if (!targetId.equals(other.targetId))
			return false;
		if (totalCost == null) {
			if (other.totalCost != null)
				return false;
		} else if (!totalCost.equals(other.totalCost))
			return false;
		return true;
	}
	
	
}
