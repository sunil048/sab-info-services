package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableJpaRepositories
@EnableFeignClients
public class SabInfoServicesApplication  {
	public static void main(String[] args) {
		SpringApplication.run(SabInfoServicesApplication.class, args);
	}
	
	@RequestMapping("/status")
	public String getStatus() {
		return "Server is up and running.........";
	}
	
}


