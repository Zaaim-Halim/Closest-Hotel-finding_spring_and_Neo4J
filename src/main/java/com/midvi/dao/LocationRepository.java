package com.midvi.dao;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.midvi.models.Location;

public interface LocationRepository extends Neo4jRepository<Location, Long> {
    
	@Query("OPTIONAL MATCH (node1:Node {id: $ref1}), (node2:Node {id: $ref2}) "
			+ "MERGE (node1)-[r:CONNECTED {distance: distance(point({latitude:toFloat(node1.lat), "
			+ "longitude:toFloat(node1.lon)}),point({latitude:toFloat(node2.lat), "
			+ "longitude:toFloat(node2.lon)}))}]->(node2) ")
	void connectTwoNodes(Long ref1, Long ref2);
    
	@Query("MATCH (node1:Node {id: $ref1}) ,(node2:Node {id:$ref2}) "
			+ "RETURN DISTINCT size((node1)--(node2)) as s")
	int isNodesAlreadyConnected(Long ref1, Long ref2);
	
	@Query("MATCH (node1:Node {id: $ref1}) ,(node2:Hotel {id:$ref2}) "
			+ "RETURN DISTINCT size((node1)--(node2)) as s")
	int isNodesAlreadyConnectedToHotel(Long ref1, Long ref2);

	@Query("MATCH (node:Node {id: $nodeId}) , (hotel:Hotel {id: $hotelId}) "
			+ " WITH distance(point({latitude:toFloat(node.lat), longitude:toFloat(node.lon)}), "
			+ " point({latitude:toFloat(hotel.lat), longitude:toFloat(hotel.lon)})) as distance"
			+ " RETURN distance")
	Double calculateDistanceBetweenTwoNodes(Long hotelId, Long nodeId);
	
	@Query("MATCH (node:Node {id: $nodeId}) , (hotel:Hotel {id: $HotelId}) "
			+ "MERGE (node)-[r:CONNECTED {distance: $distance}]->(hotel)  ")
	void connectHotelToTheClosestNode(Long HotelId, Long nodeId, Double distance);
    
	@Query("MATCH (n:Node)-[r:CONNECTED]-(p:Node) WITH n , count(*) as nodes WHERE nodes=1 RETURN n")
	List<Location> findextreamNodes();
    
	@Query("MATCH (n:Node{id:$id})-[:CONNECTED*1..13]-(p:Node) RETURN DISTINCT p")
	List<Location> nodesInTHeSamePath(Long id);
    
	@Query("MATCH (node1:Node {id: $node1Id}) , (node2:Node {id: $node2Id}) "
			+ "CREATE (node1)-[r:CONNECTED {distance: $d}]->(node2)  ")
	void connectTwoNodesGiveDistance(Long node1Id, Long node2Id, Double d);
	
	
}
