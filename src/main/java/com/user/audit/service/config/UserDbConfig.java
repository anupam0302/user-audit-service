package com.user.audit.service.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(
		basePackages = "com.user.audit.service.userrepository",
		entityManagerFactoryRef = "userEntityManagerFactory",
		transactionManagerRef = "userTranasctionManager"
)
public class UserDbConfig {

	@Primary
	@Bean(name = "userDataSource")
	DataSource userDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Primary
	@Bean(name="userEntityManagerFactory")
	LocalContainerEntityManagerFactoryBean userEntityManagerFactory(
			EntityManagerFactoryBuilder builder,
			@Qualifier("userDataSource") DataSource dataSource
			) {
		return builder
				.dataSource(dataSource)
				.packages("com.user.audit.service.entity")
				.persistenceUnit("userPU")
				.build();
	}
	
	@Primary
	@Bean(name = "userTransactionManager")
	PlatformTransactionManager userTransactionManager(
			@Qualifier("userEntityManagerFactory") EntityManagerFactory factory) {
		return new JpaTransactionManager(factory);
	}
	
}