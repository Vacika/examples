package com.vaskojovanoski.multitenancy_example.configuration.database;

import com.vaskojovanoski.multitenancy_example.configuration.security.filters.DataSourceRoutingFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataSourceRoutingFilterTest {

    private DataSourceRoutingFilter filter;

    @BeforeEach
    void setUp() {
        filter = new DataSourceRoutingFilter();
    }

    @Test
    void testDoFilterWithTenantHeader() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain chain = new MockFilterChain();

        request.addHeader("X-Tenant", "tenant1");

        filter.doFilter(request, response, chain);

        assertEquals("tenant1", TenantContext.getTenantId());
    }

    @Test
    void testDoFilterWithoutTenantHeader() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain chain = new MockFilterChain();

        filter.doFilter(request, response, chain);

        assertEquals("default", TenantContext.getTenantId());
    }
}
