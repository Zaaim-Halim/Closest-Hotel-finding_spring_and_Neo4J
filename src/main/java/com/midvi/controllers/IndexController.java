package com.midvi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.midvi.dto.ShortestPathDto;
import com.midvi.services.HotelFindingService;


@RestController
class IndexController {
	
	@Autowired
	private HotelFindingService hotelFindingService;
	
	@RequestMapping(value = "/find/{lat}/{lon}/{nbResult}",method = RequestMethod.GET,
			produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public List<ShortestPathDto> findClosestHotels(@PathVariable("lat") Double lat,@PathVariable("lon") Double lon,@PathVariable("nbResult") String nbResult) {
		List<ShortestPathDto> shortestpaths = hotelFindingService.findTheClosestHotels(lat, lon,Integer.parseInt(nbResult));
		return shortestpaths;
	}
	
	

}
