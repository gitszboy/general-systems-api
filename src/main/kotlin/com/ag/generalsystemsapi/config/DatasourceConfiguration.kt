package com.ag.generalsystemsapi.config

import liquibase.integration.spring.SpringLiquibase
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
class DatasourceConfiguration {

    @Configuration
    @EnableTransactionManagement
    @EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        basePackages = ["com.ag.generalsystemsapi.api"]
    )

    class ApiDataSourceConfig {
        @Primary
        @Bean(name = ["dataSource"])
        @ConfigurationProperties(prefix = "apidb.datasource")
        fun dataSource(): DataSource {
            return DataSourceBuilder.create().build()
        }

        @Primary
        @Bean(name = ["entityManagerFactory"])
        fun entityManagerFactory(
            builder: EntityManagerFactoryBuilder,
            @Qualifier("dataSource") dataSource: DataSource?
        ): LocalContainerEntityManagerFactoryBean {
            return builder
                .dataSource(dataSource)
                .packages("com.ag.generalsystemsapi.api")
                .persistenceUnit("api")
                .build()
        }

        @Primary
        @Bean(name = ["transactionManager"])
        fun transactionManager(
            @Qualifier("entityManagerFactory") entityManagerFactory: EntityManagerFactory?
        ): PlatformTransactionManager {
            return JpaTransactionManager(entityManagerFactory!!)
        }

        @Primary
        @Bean
        @ConfigurationProperties(prefix = "datasource.primary-liquibase.liquibase")
        fun  primaryLiquibaseProperties(): LiquibaseProperties {
            return LiquibaseProperties()
        }

        @Primary
        @Bean("liquibase")
        fun  primaryLiquibase(): SpringLiquibase {
            return springLiquibase(dataSource(), primaryLiquibaseProperties());
        }

        fun springLiquibase(dataSource: DataSource, properties: LiquibaseProperties): SpringLiquibase {
            var liquibase: SpringLiquibase = SpringLiquibase();
            liquibase.setDataSource(dataSource);
            liquibase.setChangeLog(properties.getChangeLog());
            liquibase.setContexts(properties.getContexts());
            liquibase.setDefaultSchema(properties.getDefaultSchema());
            liquibase.setDropFirst(properties.isDropFirst());
            liquibase.setShouldRun(properties.isEnabled());
            liquibase.setLabels(properties.getLabels());
            liquibase.setChangeLogParameters(properties.getParameters());
            liquibase.setRollbackFile(properties.getRollbackFile());
            return liquibase;
        }
    }

    @Configuration
    @EnableTransactionManagement
    @EnableJpaRepositories(
        entityManagerFactoryRef = "tpEntityManagerFactory",
        transactionManagerRef = "tpTransactionManager",
        basePackages = ["com.ag.generalsystemsapi.thirdparty"]
    )
    class TpDataSourceConfig {
        @Bean(name = ["tpDataSource"])
        @ConfigurationProperties(prefix = "thirdpartydb.datasource")
        fun coreDataSource(): DataSource {
            return DataSourceBuilder.create().build()
        }

        @Bean(name = ["tpEntityManagerFactory"])
        fun tpEntityManagerFactory(
            builder: EntityManagerFactoryBuilder,
            @Qualifier("tpDataSource") tpDataSource: DataSource?
        ): LocalContainerEntityManagerFactoryBean {
            val props = mutableMapOf<String, Any>()
            props.put("hibernate.proc.param_null_passing", true)
            return builder
                .dataSource(tpDataSource)
                .packages("com.ag.generalsystemsapi.thirdparty")
                .persistenceUnit("tp")
                .properties(props)
                .build()
        }

        @Bean(name = ["tpTransactionManager"])
        fun tqTransactionManager(
            @Qualifier("tpEntityManagerFactory") tpEntityManagerFactory: EntityManagerFactory?
        ): PlatformTransactionManager {
            return JpaTransactionManager(tpEntityManagerFactory!!)
        }
    }
}