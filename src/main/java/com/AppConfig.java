package com;


import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
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
	/*
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	 
	return builder
	.setConnectTimeout(Duration.ofMillis(3000))
	.setReadTimeout(Duration.ofMillis(3000))
	.build();
	}
 */
	@Bean
	public RestTemplate restTemplate() {
	 
	SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
	factory.setConnectTimeout(3000);
	factory.setReadTimeout(3000);
	return new RestTemplate(factory);
	}
	@Bean
	@Primary
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource primaryDataSource() {
	    return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "secDB")
	@ConfigurationProperties(prefix="spring.datasource1")
	public DataSource psecondryDataSource() {
	    return DataSourceBuilder.create().build();
	}
	
	/*
	 * @Bean(name="sabInfoDBProperties")
	@ConfigurationProperties("spring.datasource1")
	public DataSourceProperties sabInfoDBProperties() {
		return new DataSourceProperties();
	}
	
	
	@Bean(name="mydatasource")
	public DataSource sabInfoDataSource(@Qualifier("sabInfoDBProperties") DataSourceProperties sabInfoDBProperties) {
		return (DataSource) sabInfoDBProperties.initializeDataSourceBuilder().build();
	}
	 * 
	 * 
	 */
	
}
