package com.midvi.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.web.context.request.RequestContextListener;

@Configuration
@EnableNeo4jRepositories(basePackages = "com.midvi.dao")
public class AppConfig {

	
	@Bean
	public RequestContextListener requestContextListener() {
	    return new RequestContextListener();
	}
    
	
}
