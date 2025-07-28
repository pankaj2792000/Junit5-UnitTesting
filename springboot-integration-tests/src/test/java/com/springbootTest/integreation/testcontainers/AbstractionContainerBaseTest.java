package com.springbootTest.integreation.testcontainers;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

public abstract class AbstractionContainerBaseTest {

    private static final MySQLContainer mySQLContainer;

    static {
        mySQLContainer = new MySQLContainer("mysql:latest")
                .withUsername("root")
                .withPassword("root")
                .withDatabaseName("ems");
        mySQLContainer.start();
    }

    @DynamicPropertySource
    public void dynamicPropertySource(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);


    }
}
