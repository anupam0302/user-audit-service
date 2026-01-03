package com.user.audit.service.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

/*
 * DB	Annotation
Cassandra	@EnableCassandraRepositories
Redis	@EnableRedisRepositories
Elasticsearch	@EnableElasticsearchRepositories
Couchbase	@EnableCouchbaseRepositories
Neo4j	@EnableNeo4jRepositories
 * 
 */

@Configuration
@EnableJpaRepositories(
    basePackages = "com.user.audit.service.auditrepository",
    entityManagerFactoryRef = "auditEntityManagerFactory",
    transactionManagerRef = "auditTransactionManager"
)
public class AuditDbConfig {

    @Bean(name = "auditDataSource")
    DataSource auditDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "auditEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean auditEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("auditDataSource") DataSource dataSource) {

        return builder
                .dataSource(dataSource)
                .packages("com.user.audit.service.entity")
                .persistenceUnit("auditPU")
                .build();
    }

    @Bean(name = "auditTransactionManager")
    PlatformTransactionManager auditTransactionManager(
            @Qualifier("auditEntityManagerFactory") EntityManagerFactory factory) {

        return new JpaTransactionManager(factory);
    }
}