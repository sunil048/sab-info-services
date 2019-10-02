package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages= {"com.sabtok.persistance.entity","com.sabinfo"})
@ComponentScan(basePackages= {"com"})
public class SabInfoServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SabInfoServicesApplication.class, args);
	}
}
