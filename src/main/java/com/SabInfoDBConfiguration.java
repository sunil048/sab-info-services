package com;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages= {})
public class SabInfoDBConfiguration {
	
	/*@Bean
	@ConfigurationProperties("spring.datasource2")
	public DataSourceProperties sabInfoDBProperties() {
		return new DataSourceProperties();
	}
	
	
	@Bean
	public DataSource sabInfoDataSource(@Qualifier("sabInfoDBProperties") DataSourceProperties sabInfoDBProperties) {
		return sabInfoDBProperties.initializeDataSourceBuilder().build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean getSabInfoFactory(@Qualifier("sabInfoDataSource")DataSource sabInfoDataSource,EntityManagerFactoryBuilder builder) {
		return builder.dataSource(sabInfoDataSource).packages("com.sabinfo.sabtok.wiki.model").build();
	}
	
	@Primary
	@Bean
	public PlatformTransactionManager sabInfoTransactionManager(EntityManagerFactory factory) {
		return new JpaTransactionManager(factory);
	}*/
}
