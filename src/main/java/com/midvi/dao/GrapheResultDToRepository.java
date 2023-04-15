package com.midvi.dao;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.midvi.models.GrapheResultDto;


public interface GrapheResultDToRepository extends Neo4jRepository<GrapheResultDto,Long>{
	@Query("MATCH (source:Node {id: $nodeId}), (target:Hotel) "
			+ "CALL gds.shortestPath.astar.stream('graph', { "
			+ "    sourceNode: source, "
			+ "    targetNode: target, "
			+ "    latitudeProperty: 'lat', "
			+ "    longitudeProperty: 'lon', "
			+ "    relationshipWeightProperty: 'distance' "
			+ "}) "
			+ "YIELD index, sourceNode, targetNode, totalCost, nodeIds, costs, path "
			+ "RETURN ({"
			+ "    sourceId:gds.util.asNode(sourceNode).id , "
			+ "    targetId:gds.util.asNode(targetNode).id , "
			+ "    totalCost:totalCost, "
			+ "   nodeIds:[nodeId IN nodeIds | gds.util.asNode(nodeId).id] , "
			+ "    costs:costs })"
			+ "ORDER BY totalCost LIMIT $hotelNumber")
     List<GrapheResultDto> findtheClosestHotels(Long nodeId, int hotelNumber);
}
