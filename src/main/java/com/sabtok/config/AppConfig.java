package com.sabtok.config;

import java.util.Properties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	@ConditionalOnMissingBean
	@Bean
	public BuildProperties buildProperties() {
	    Properties properties = new Properties();
	    properties.put("group", "com.sabinfo");
	    properties.put("artifact", "sab-info-services");
	    properties.put("version", "2.0");
	    return new BuildProperties(properties);
	}

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

}
