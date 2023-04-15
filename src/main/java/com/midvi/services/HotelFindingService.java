package com.midvi.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midvi.dao.GrapheResultDToRepository;
import com.midvi.dao.LocationRepository;
import com.midvi.dto.ShortestPathDto;
import com.midvi.models.GrapheResultDto;
import com.midvi.models.Hotel;
import com.midvi.models.Location;
import com.midvi.utils.HotelsMapWrraper;
import com.midvi.utils.NodeMapWrapper;

@Service
public class HotelFindingService {
	@Autowired
	private LocationRepository locationrepo;
    @Autowired
    private GrapheResultDToRepository grapheResultDToRepository;
    @Autowired
    private NodeMapWrapper nodesMapWrapper;
    @Autowired
    private HotelsMapWrraper hotelsMapWrapper;
    private static Double minDistance ;
    
	public List<ShortestPathDto> findTheClosestHotels(Double lat,Double lon,int hotelNumber){
		Long nodeId = theClosestNodeToThecurrentLocation(lat, lon);
		List<GrapheResultDto> graphResult = grapheResultDToRepository.findtheClosestHotels(nodeId,hotelNumber) ;
		System.out.println(graphResult.toString());
		List<ShortestPathDto> shortestPaths = new ArrayList<>();
		Map<Long,Location> nodeMaps = nodesMapWrapper.getNodeMap();
		Map<Long,Hotel> hotelsMap = hotelsMapWrapper.getHotelsMap();
		for(GrapheResultDto gd : graphResult) {
			ShortestPathDto oneShortestPath =  new ShortestPathDto();
			oneShortestPath.setTotalCost(BigDecimal.valueOf(gd.getTotalCost()+minDistance).setScale(2, RoundingMode.DOWN).doubleValue());
			oneShortestPath.setDestination(hotelsMap.get(gd.getTargetId()));
			oneShortestPath.setSource(new Location(1L, lat, lon));
			List<Long> ids = gd.getNodeIds();
			ids.remove(ids.size()-1); // the last one is for the hotel
			for(Long id : ids) {
				oneShortestPath.getPath().add(nodeMaps.get(id));
			}
			shortestPaths.add(oneShortestPath);
		}
		return shortestPaths;
	}

	private Long theClosestNodeToThecurrentLocation(Double lat , Double lon) {
		List<Location> locations = locationrepo.findAll();
		Map<Double,Location> nodes = new HashMap<>();
		for(Location l : locations) {
			nodes.put(calculateDistanceInMeters(lat, lon, l.getLat(), l.getLon()), l);
			
		}
		minDistance = Collections.min(nodes.keySet());
		return nodes.get(minDistance).getId();
	}
	
	//helper
	public double calculateDistanceInMeters(double lat1, double long1, double lat2, double long2) {

		double dist = org.apache.lucene.util.SloppyMath.haversinMeters(lat1, long1, lat2, long2);
		return dist;
	}

}
