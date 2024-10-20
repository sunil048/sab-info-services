package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableJpaRepositories
@EnableFeignClients
@EnableScheduling
public class SabInfoServicesApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(SabInfoServicesApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SabInfoServicesApplication.class);
	}

	@RequestMapping("/status")
	public String getStatus() {
		return "Server is up and running.........";
	}
	
}


