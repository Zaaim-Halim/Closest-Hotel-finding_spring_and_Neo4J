package com.midvi.dao;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import com.midvi.models.Hotel;


public interface HotelRepository extends Neo4jRepository<Hotel,Long> {
    
}
