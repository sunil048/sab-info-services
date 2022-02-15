package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sabtok.report.controller.JobController;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
		  MongoAutoConfiguration.class,
		  
		  MongoDataAutoConfiguration.class,
		  EmbeddedMongoAutoConfiguration.class
		})
@EntityScan(basePackages= {"com.sabtok.persistance.entity","com.sabinfo"})
@ComponentScan(basePackages= {"com"})
public class SabInfoServicesApplication extends SpringBootServletInitializer  {

	private static final Logger logger = LoggerFactory.getLogger(SabInfoServicesApplication.class);
	
	public static void main(String[] args) {
		logger.info("Starting application");
		SpringApplication.run(SabInfoServicesApplication.class, args);
	}
	
	@RequestMapping("/")
	public String getStatus() {
		return "Server is up and running.........";
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SabInfoServicesApplication.class);
	}
	
}


