package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableJpaRepositories
public class SabInfoServicesApplication  {
	public static void main(String[] args) {
		SpringApplication.run(SabInfoServicesApplication.class, args);
	}
	
	@RequestMapping("/status")
	public String getStatus() {
		return "Server is up and running.........";
	}
	
}


