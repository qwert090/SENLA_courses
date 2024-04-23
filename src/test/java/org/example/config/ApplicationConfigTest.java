package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import liquibase.integration.spring.SpringLiquibase;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@PropertySource("classpath:h2.properties")
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("org.example")
@RequiredArgsConstructor
public class ApplicationConfigTest {
    @Value("${h2db.url}")
    private String url;
    @Value("${h2db.username}")
    private String username;
    @Value("${h2db.password}")
    private String password;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        return mapper;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public SpringLiquibase springLiquibase(DataSource dataSource) {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog("changelog.xml");
        return springLiquibase;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource, LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(true);
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("org.example.entity");
        factoryBean.setJpaVendorAdapter(vendorAdapter);

        return factoryBean;
    }

    @Bean
    public EntityManager entityManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return entityManagerFactory.getObject().createEntityManager();
    }

    @Bean
    public CriteriaBuilder criteriaBuilder(EntityManager entityManager) {
        return entityManager.getCriteriaBuilder();
    }
}
