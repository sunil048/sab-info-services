package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages= {"com.sabtok.persistance.entity","com.sabinfo"})
@ComponentScan(basePackages= {"com"})
public class SabInfoServicesApplication extends SpringBootServletInitializer  {

	public static void main(String[] args) {
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


