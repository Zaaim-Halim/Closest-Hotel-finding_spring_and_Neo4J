package com.midvi.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.midvi.dao.HotelRepository;
import com.midvi.dao.LocationRepository;
import com.midvi.models.Hotel;
import com.midvi.models.Location;
import com.midvi.utils.HotelsMapWrraper;
import com.midvi.utils.NodeMapWrapper;

@Configuration
@ComponentScan(basePackages ="com.midvi.utils")
public class BeanConfig {
	
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private LocationRepository locationrepo;
	@Bean
	public NodeMapWrapper NodesMap(){
		List<Location> locations = locationrepo.findAll();
		Map<Long,Location> nodes = new HashMap<>();
		for(Location l : locations) {
			nodes.put(l.getId(), l);
			
		}
		return new NodeMapWrapper(nodes);
	}
	@Bean
	public HotelsMapWrraper HotelMap(){
		List<Hotel> hotelsList = hotelRepository.findAll();
		Map<Long,Hotel> hotelMap = new HashMap<>();
		for(Hotel h : hotelsList) {
			hotelMap.put(h.getId(), h);
			
		}
		return new HotelsMapWrraper(hotelMap);
	}

}
