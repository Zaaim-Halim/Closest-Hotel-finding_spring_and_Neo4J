package com.midvi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.midvi.dao.HotelRepository;
import com.midvi.models.Hotel;
import com.midvi.services.ConnectingNodesService;
import com.midvi.services.JSonParser;

@RestController
public class HotelController {
    
	@Autowired
	private HotelRepository hotelRepository;
    
	@Autowired
	private JSonParser jsonParser;
	
	@Autowired
	private ConnectingNodesService connectingNodesService;
	
	@GetMapping("/save/{name}/{latitude}/{langitude}/{description}")
	public Hotel saveHotel(@PathVariable("name") String name,@PathVariable("latitude") Double latitude,
			@PathVariable("langitude") Double langitude,@PathVariable("description") String description)
	{
		Hotel h = new Hotel();
		return hotelRepository.save(h);
	}
	
	@GetMapping("/get/{id}") 
	public Hotel parce(@PathVariable("id") Long id) { 
		
		return hotelRepository.findById(id).get();
	}
	@GetMapping("/parse") 
	public void parce() { 
		
		//jsonParser.loadWaysNodesToDataBase();
		//jsonParser.loadNodesHotelsToDataBase();
		//connectingNodesService.connectNodes();
		//connectingNodesService.addRelationBetweenWays();
		//connectingNodesService.connectNodesInThExtreamEnd();
		//connectingNodesService.connectHotelsToNodes();
	}
	
}
