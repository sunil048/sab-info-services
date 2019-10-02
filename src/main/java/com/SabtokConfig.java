package com;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder.EntityManagerFactoryBeanCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages= {"com.sabtok.persistance.dao"},entityManagerFactoryRef="sabtokDSFactory",transactionManagerRef="sabtokTransactionManager")
public class SabtokConfig {
	
	@Primary
	@Bean
	@ConfigurationProperties("spring.datasource1")
	public DataSourceProperties sabtokDBProperties() {
		return new DataSourceProperties();
	}
	
	@Primary
	@Bean
	public DataSource sabtokDataSource(@Qualifier("sabtokDBProperties") DataSourceProperties sabtokDBProperties) {
		return sabtokDBProperties.initializeDataSourceBuilder().build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean sabtokDSFactory(@Qualifier("sabtokDataSource")DataSource sabtokDataSource,EntityManagerFactoryBuilder builder) {
		return builder.dataSource(sabtokDataSource).packages("com.sabtok.persistance.entity").build();
	}
	
	@Primary
	@Bean
	public PlatformTransactionManager sabtokTransactionManager(EntityManagerFactory sabtokDSFactory) {
		return new JpaTransactionManager(sabtokDSFactory);
	}
}
