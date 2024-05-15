package org.example.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import javax.sql.DataSource;

@PropertySource("classpath:h2.properties")
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("org.example")
@RequiredArgsConstructor
@EnableMethodSecurity
public class ApplicationConfigTest{
    @Value("${h2db.url}")
    private String url;
    @Value("${h2db.username}")
    private String username;
    @Value("${h2db.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
