package com.vaskojovanoski.multitenancy_example.configuration.security.filters;

import com.vaskojovanoski.multitenancy_example.configuration.database.TenantContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Log4j2
public class DataSourceRoutingFilter extends GenericFilterBean {
    private static final List<String> allowedTenantIdValues = Arrays.asList("default", "tenant1", "tenant2");
    public static final String HEADER_NAME = "X-Tenant";

    public DataSourceRoutingFilter() {
        log.info("Adding DataSourceRoutingFilter to existing filter chain");
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        final String dataSourceHeaderValue = httpServletRequest.getHeader(HEADER_NAME);
        String tenantId = "default";
        if (allowedTenantIdValues.contains(dataSourceHeaderValue)) {
            tenantId = dataSourceHeaderValue;
        }
        TenantContext.setTenantId(tenantId);
        chain.doFilter(request, response);
    }
}