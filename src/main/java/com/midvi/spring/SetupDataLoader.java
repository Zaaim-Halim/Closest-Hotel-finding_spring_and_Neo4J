package com.midvi.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.midvi.dao.LocationRepository;
import com.midvi.models.Location;
import com.midvi.services.JSonParser;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;
    @Autowired
	private JSonParser jsonParser;
    @Autowired
    private LocationRepository locationrepo;
    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (!alreadySetup) {
          //jsonParser.loadNodesHotelsToDataBase();
         //jsonParser.loadWays(); // without tags !!!
  		  //jsonParser.loadRelations(); 
  		  //jsonParser.loaodWaysNodes(); // without tags !!!
  		  
  		  //jsonParser.loadWaysNodesToDataBase();
  	  
  		  //connectingNodesService.connectNodes();
  		  //connectingNodesService.addRelationBetweenWays();
  		  //connectingNodesService.connectHotelsToNodes();
  		  //connectingNodesService.connectNodesInThExtreamEnd();
             
        }
        
        alreadySetup = true;
    }

}