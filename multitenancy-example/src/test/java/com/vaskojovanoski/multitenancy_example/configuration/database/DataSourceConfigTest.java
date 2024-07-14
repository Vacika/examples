package com.vaskojovanoski.multitenancy_example.configuration.database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DataSourceConfigTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void testDataSourceIsNotNull() {
        assertNotNull(dataSource);
    }

    @Test
    void testRoutingDataSource() {
        assertTrue(dataSource instanceof AbstractRoutingDataSource);
    }
}