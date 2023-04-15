package com.midvi.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.midvi.dao.HotelRepository;
import com.midvi.dao.LocationRepository;
import com.midvi.models.Hotel;
import com.midvi.models.Location;
import com.midvi.streetMapModels.node;
import com.midvi.streetMapModels.nodes;
import com.midvi.streetMapModels.relations;
import com.midvi.streetMapModels.tag;
import com.midvi.streetMapModels.ways;

@Service
public class JSonParser {
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private LocationRepository nodeRepository;
	
	@Value("${fes.nodes.json.path}")
	private String FES_NODES;
	@Value("${fes.nodes.ser.path}")
	private String FES_NODES_SER;
	
	@Value("${fes.json.ways.nodes}")
	private String FES_ROADS_NODES;
	@Value("${fes.json.ways.nodes.ser}")
	private String FES_ROADS_NODES_SER;
	
	@Value("${fes.json.ways}")
	private String FES_WAYS;
	@Value("${fes.json.ways.ser}")
	private String FES_WAYS_SER;
	
	@Value("${fes.json.relation}")
	private String FES_RELATIONS;
	@Value("${fes.json.relation.ser}")
	private String FES_RELATIONS_SER;
	

	public void loadNodes(){
		nodes nodes = null;
		Gson gson = new Gson();
		try {
			Reader reader = Files.newBufferedReader(Paths.get(FES_NODES));
			nodes = gson.fromJson(reader,nodes.class);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// serialize the object 
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream(FES_NODES_SER);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(nodes);
	         out.close();
	         fileOut.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	public void loaodWaysNodes() {
		nodes nodes = null;
		Gson gson = new Gson();
		try {
			Reader reader = Files.newBufferedReader(Paths.get(FES_ROADS_NODES));
			nodes = gson.fromJson(reader,nodes.class);
			System.out.println(nodes.getNodes().size());
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// serialize the object 
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream(FES_ROADS_NODES_SER);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(nodes);
	         out.close();
	         fileOut.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	public void loadWays(){
		
		ways ways = null;
		Gson gson = new Gson();
		try {
			Reader reader = Files.newBufferedReader(Paths.get(FES_WAYS));
			ways = gson.fromJson(reader,ways.class);
			System.out.println(ways.getWay().size());
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// serialize the object 
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream(FES_WAYS_SER);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(ways);
	         out.close();
	         fileOut.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	public void loadRelations(){
		relations relations = null;
		Gson gson = new Gson();
		try {
			Reader reader = Files.newBufferedReader(Paths.get(FES_RELATIONS));
			relations = gson.fromJson(reader,relations.class);
			System.out.println(relations.getRelation().size());
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// serialize the object 
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream(FES_RELATIONS_SER);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(relations);
	         out.close();
	         fileOut.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
		  
	}
    
	
	
	
	private nodes readAllNodesFromFile() {
		nodes nodes = null;
		try {
			FileInputStream fileIn = new FileInputStream(FES_NODES_SER);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			nodes = (nodes) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		return nodes;
	}
	
	
	public void loadNodesHotelsToDataBase() {
		nodes nodes = readAllNodesFromFile();
		System.out.println(nodes.toString());
		Hotel hotel = new Hotel();
		for (node node : nodes.getNodes()) {
			hotel.setId(node.getId());
			hotel.setLat(node.getLat());
			hotel.setLon(node.getLon());

			if (node.getTag() != null && node.getTag().size() > 0) {
				for (tag tag : node.getTag()) {
					hotel.getInfos().put(tag.getK(), tag.getV());
					if ("name".equalsIgnoreCase(tag.getK())) {
						hotel.setName(tag.getV());
					}
					if ("name:ar".equalsIgnoreCase(tag.getK())) {
						hotel.setNameAR(tag.getV());
					}
					if ("addr:city".equalsIgnoreCase(tag.getK())) {
						hotel.setAddress(tag.getV());
					}

				}
			}
			hotelRepository.save(hotel);

		}
	}
	
	public void loadWaysNodesToDataBase() {
		nodes nodes = null;
		try {
			FileInputStream fileIn = new FileInputStream(FES_ROADS_NODES_SER);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			nodes = (nodes) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		List<node> nodesL =  nodes.getNodes().subList(10000,nodes.getNodes().size()-1); //0-1000  1000,10000 10000,20000
		for(node node : nodesL) {
			Location location = new Location();
			location.setId(node.getId());
			location.setLat(node.getLat());
			location.setLon(node.getLon());
			nodeRepository.save(location);
			
		}
	}
	
	public ways loadWaysFromSerilizedFile() {
		ways ways = null;
		try {
			FileInputStream fileIn = new FileInputStream(FES_WAYS_SER);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			ways = (ways) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
        return ways;
		
	}
	
	public relations loadRelationsFromSerilizedFile() {
		relations relations = null;
		try {
			FileInputStream fileIn = new FileInputStream(FES_RELATIONS_SER);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			relations = (relations) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
        return relations;
		
	}
}
