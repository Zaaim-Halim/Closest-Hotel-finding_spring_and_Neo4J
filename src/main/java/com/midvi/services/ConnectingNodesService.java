package com.midvi.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midvi.dao.HotelRepository;
import com.midvi.dao.LocationRepository;
import com.midvi.models.Hotel;
import com.midvi.models.Location;
import com.midvi.streetMapModels.member;
import com.midvi.streetMapModels.nd;
import com.midvi.streetMapModels.relation;
import com.midvi.streetMapModels.relations;
import com.midvi.streetMapModels.way;
import com.midvi.streetMapModels.ways;

/**
 * @author ZAAIM HALIM
 *
 */
@Service
public class ConnectingNodesService {

	@Autowired
	private JSonParser fileLoader;
	@Autowired
	private LocationRepository nodeRepository;
	@Autowired
	private HotelRepository hotelRepository;

	private static boolean loaded = false;
	private static boolean loadedRelations = false;
	private static boolean connectedHotelsToNodes = false;
	private static boolean connectedExtreamNodes = false;

	public void connectNodes() {
		ways ways = fileLoader.loadWaysFromSerilizedFile();
		List<nd> nds = null;
		if (!loaded) {
			loaded = true;
			List<way> waysList = ways.getWay().subList(0, 2000);
			for (int j = 0; j < waysList.size(); j++) {
				nds = waysList.get(j).getNd();
				 System.out.println(waysList.indexOf(waysList.get(j)));
				for (int i = 0; i < nds.size() - 1; i++) {
                    if(nds.get(i).getRef()== null ||  nds.get(i + 1).getRef() == null) continue; 
                    if(nodeRepository.isNodesAlreadyConnected(nds.get(i).getRef(), nds.get(i + 1).getRef())==0)
					   nodeRepository.connectTwoNodes(nds.get(i).getRef(), nds.get(i + 1).getRef());
					}

				}
			   
				///waysList.remove(j);

			}
		
	}

	public void addRelationBetweenWays() {
		ways ways = fileLoader.loadWaysFromSerilizedFile();
		List<way> waysList = ways.getWay();
		relations relations = fileLoader.loadRelationsFromSerilizedFile();
		List<member> memebers = null;
		way fisrstWay = null;
		way secondWay = null;
		List<relation> relationList = relations.getRelation();
		if (!loadedRelations) {
			loadedRelations = true;
			for (int i = 0; i < relationList.size(); i++) {
				memebers = relationList.get(i).getMember();
				for (int j = 0; j < memebers.size() - 1; j++) {
					Long ref1 = memebers.get(j).getRef();
					Long ref2 = memebers.get(j + 1).getRef();
					if (findWayFromRef(waysList, ref1) != null && findWayFromRef(waysList, ref2) != null) {

						fisrstWay = findWayFromRef(waysList, ref1);
						secondWay = findWayFromRef(waysList, ref2);
						Long node1Ref = fisrstWay.getNd().get(fisrstWay.getNd().size() - 1).getRef();
						Long node2Ref = secondWay.getNd().get(0).getRef();

						if (nodeRepository.isNodesAlreadyConnected(node1Ref, node2Ref) == 0 && node1Ref != node2Ref)
							System.out.println("========================== "
									+ nodeRepository.isNodesAlreadyConnected(node1Ref, node2Ref));
						nodeRepository.connectTwoNodes(node1Ref, node2Ref);
					}
				}
			}
		}

	}

	// helper
	private way findWayFromRef(List<way> ways, Long ref) {
		for (way w : ways) {
			if (w.getId().equals(ref))
				return w;
		}
		return null;
	}

	// connect hotels to other nodes
	/**
	 * the idea is to look for the closest node to the Hotel-node in term of
	 * distance and then connect that hotel node to the regular node ......
	 */
	public void connectHotelsToNodes() {
		List<Hotel> Hotels = hotelRepository.findAll();
		List<Location> nodes = nodeRepository.findAll();
		System.out.println(nodes.size()+"      "+Hotels.size());
		Double distance = null;
		Map<Double, Location> nodeDistence = new HashMap<>();
		if (!connectedHotelsToNodes) {
			connectedHotelsToNodes = true;
			
			for (Hotel h : Hotels) {
				for (Location n : nodes) {
					//distance = nodeRepository.calculateDistanceBetweenTwoNodes(h.getId(), n.getId());
					distance = calculateDistanceInMeters(h.getLat(), h.getLon(), n.getLat(),n.getLon());
					nodeDistence.put(distance, n);
				}
				System.out.println("size of nodeDistence = "+nodeDistence.size());
				distance = findClosestNode(nodeDistence);//shortest distance
				//if (nodeRepository.isNodesAlreadyConnectedToHotel(h.getId(), nodeDistence.get(distance).getId())==null) {
					nodeRepository.connectHotelToTheClosestNode(h.getId(), nodeDistence.get(distance).getId(),
							distance);
				//}
				nodeDistence.clear();
			}
		}
	}
    public void connectNodesInThExtreamEnd() {
 
    	List<Location> nodes = nodeRepository.findAll();
    	List<Location> extremNodesList = nodeRepository.findextreamNodes();
    	System.out.println("SIZE IS = "+extremNodesList.size());
    	
    	List<Location> nodesInThepath = null;
    	Map<Double, Location> nodeDistence = new HashMap<>();
    	Double distance = null;
        
    	if (!connectedExtreamNodes) {
    		connectedExtreamNodes = true;
    		System.out.println("============= started ================");
			for (Location n :extremNodesList) {
				System.out.println(n.getId());
				//if(true) break;
				nodesInThepath = nodeRepository.nodesInTHeSamePath(n.getId());
				System.out.println("nodes in the path : "+nodesInThepath.size());
				nodes.removeAll(nodesInThepath);
				System.out.println("=========== in the node list ======");
				// now find the closest 
				for (Location rnode : nodes) {
					distance = calculateDistanceInMeters(n.getLat(), n.getLon(), rnode.getLat(), rnode.getLon());
					nodeDistence.put(distance, rnode);
					
				}
				System.out.println(nodeDistence.size());
				distance = findClosestNode(nodeDistence);
		
                System.out.println("param : "+n.getId()+", "+nodeDistence.get(distance).getId() +", "+ distance);
                
				if (n.getId() !=nodeDistence.get(distance).getId() ) {
					nodeRepository.connectTwoNodesGiveDistance(n.getId(), nodeDistence.get(distance).getId(),
							distance);
				}
				nodeDistence.clear();
				nodes = nodeRepository.findAll();
				System.out.println("=========== finished with the node number : "+extremNodesList.indexOf(n) +"FROM TOTAL :"+extremNodesList+"================");
			} 
		}
    	System.out.println("======================== TIRMINATED =================================");
    }
	private Double findClosestNode(Map<Double, Location> nodeDistence) {
		nodeDistence.remove(0.0);
		return Collections.min(nodeDistence.keySet());
	}
	
	/**
	 * Calculate distance between two points in latitude and longitude taking
	 * into account height difference. If you are not interested in height
	 * difference pass 0.0. Uses Haversine method as its base.
	 * 
	 * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
	 * el2 End altitude in meters
	 * @returns Distance in Meters
	 */
	public static double distance(double lat1, double lat2, double lon1,
	        double lon2, double el1, double el2) {

	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    double height = el1 - el2;

	    distance = Math.pow(distance, 2) + Math.pow(height, 2);

	    return Math.sqrt(distance);
	}

	public double calculateDistanceInMeters(double lat1, double long1, double lat2, double long2) {

		double dist = org.apache.lucene.util.SloppyMath.haversinMeters(lat1, long1, lat2, long2);
		return dist;
	}
}
