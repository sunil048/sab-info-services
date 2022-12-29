package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.sabtok"})
@EnableJpaRepositories("com.sabtok.persistance.dao.*")
@EntityScan("com.sabtok.persistance.entity.*")
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


