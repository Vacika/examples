package com.vaskojovanoski.multitenancy_example.configuration.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MultiTenantDataSourceTest {

    private MultiTenantDataSource multiTenantDataSource;

    @BeforeEach
    void setUp() {
        multiTenantDataSource = new MultiTenantDataSource();
    }

    @Test
    void testDetermineCurrentLookupKey() {
        TenantContext.setTenantId("tenant1");
        assertEquals("tenant1", multiTenantDataSource.determineCurrentLookupKey());
        TenantContext.clear();
        assertNull(multiTenantDataSource.determineCurrentLookupKey());
    }
}