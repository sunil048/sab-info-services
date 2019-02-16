package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages= {"com.sabtok.persistance.entity"})
public class SabInfoServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SabInfoServicesApplication.class, args);
	}
}
