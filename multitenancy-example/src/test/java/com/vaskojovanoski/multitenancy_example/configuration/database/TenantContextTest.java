package com.vaskojovanoski.multitenancy_example.configuration.database;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TenantContextTest {

    @Test
    void testSetGetAndClearTenantId() {
        TenantContext.setTenantId("tenant1");
        assertEquals("tenant1", TenantContext.getTenantId());
        TenantContext.clear();
        assertNull(TenantContext.getTenantId());
    }
}