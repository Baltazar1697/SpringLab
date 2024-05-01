package ua.kpi.its.lab.data.config

import jakarta.persistence.EntityManagerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.sql.DataSource

@Configuration
@ComponentScan(basePackages = ["ua.kpi.its.lab.data"])
@EnableJpaRepositories(basePackages = ["ua.kpi.its.lab.data.repo"])
@EnableTransactionManagement
class Config {

    /**
     * Configures the data source for the embedded database.
     *
     * Uses Spring's [EmbeddedDatabaseBuilder] to configure an in-memory HSQL database.
     *
     * @return DataSource the configured data source for the application.
     */
    @Bean
    fun dataSource(): DataSource =
        EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .build()

    /**
     * Configures the [EntityManagerFactory] bean used by JPA to manage entity persistence.
     *
     * Sets the data source and package to scan for JPA entities. Additionally, it configures
     * Hibernate as the JPA vendor adapter, specifying properties such as DDL auto-generation and SQL dialect.
     *
     * @param dataSource The DataSource bean configured for the application.
     * @return LocalContainerEntityManagerFactoryBean the EntityManagerFactory instance for managing JPA entities.
     */
    @Bean
    fun entityManagerFactory(dataSource: DataSource) =
        LocalContainerEntityManagerFactoryBean().apply {
            setDataSource(dataSource)
            setPackagesToScan("ua.kpi.its.lab.data.entity")
            jpaVendorAdapter = HibernateJpaVendorAdapter()
            setJpaProperties(additionalProperties())
        }

    /**
     * Configures the transaction manager to be used for managing transactions within the Spring application.
     *
     * Utilizes [JpaTransactionManager] for managing JPA transactions, linked with the configured [EntityManagerFactory].
     *
     * @param entityManagerFactory The EntityManagerFactory bean for creating EntityManager instances.
     * @return PlatformTransactionManager the transaction manager for managing transactions.
     */
    @Bean
    fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager =
        JpaTransactionManager(entityManagerFactory)

    /**
     * Configures a bean to automatically translate any persistence-related exceptions into Spring's DataAccessException hierarchy.
     *
     * This abstraction allows for consistent exception handling across different persistence technologies.
     *
     * @return PersistenceExceptionTranslationPostProcessor the exception translation post-processor.
     */
    @Bean
    fun exceptionTranslation() =
        PersistenceExceptionTranslationPostProcessor()

    /**
     * Specifies additional properties for the JPA provider (Hibernate in this case).
     *
     * This includes properties such as the Hibernate dialect, DDL auto generation strategy, and whether to show SQL in logs.
     *
     * @return Properties the additional properties to set on the JPA provider.
     */
    private fun additionalProperties(): Properties =
        Properties().apply {
            setProperty("hibernate.hbm2ddl.auto", "create")
            setProperty("hibernate.show_sql", "true")
        }
}