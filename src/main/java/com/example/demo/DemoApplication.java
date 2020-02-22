package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.tomcat.jdbc.pool.*;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DemoApplication {
    @Value("${db.mysql-driver-class-name}")
    private String driverClassName;
    @Value("${db.mysql-server}")
    private String serverURL;
    @Value("${db.mysql-username}")
    private String userName;
    @Value("${db.mysql-password}")
    private String password;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Bean
    public DataSource dataSource() {
        PoolProperties properties = new PoolProperties();
        properties.setDriverClassName(driverClassName);
        properties.setUrl(serverURL);
        properties.setUsername(userName);
        properties.setPassword(password);
        properties.setInitialSize(34);
        properties.setMaxActive(377);
        properties.setMaxIdle(233);
        properties.setMinIdle(89);
        properties.setTimeBetweenEvictionRunsMillis(34000);
        properties.setMinEvictableIdleTimeMillis(55000);
        properties.setValidationInterval(34000);
        properties.setRemoveAbandoned(true);
        properties.setRemoveAbandonedTimeout(55);
        properties.setTestOnBorrow(true);
        properties.setValidationQuery("SELECT 1");
        DataSource ds = new DataSource();
        ds.setPoolProperties(properties);
        return ds;
    }

}
