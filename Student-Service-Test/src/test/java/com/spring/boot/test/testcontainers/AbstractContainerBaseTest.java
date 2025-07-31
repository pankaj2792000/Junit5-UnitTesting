package com.spring.boot.test.testcontainers;


import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

public abstract class AbstractContainerBaseTest {

    private static final MySQLContainer mySQLContainer;

    static {
        mySQLContainer= new MySQLContainer()
                .withUsername("test")
                .withPassword("test")
                .withDatabaseName("test");
    }

    @DynamicPropertySource
    public void dynamicPropertySource(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);


    }

}
