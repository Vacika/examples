package com.vaskojovanoski.multitenancy_example.configuration.database;

import com.vaskojovanoski.multitenancy_example.configuration.database.properties.DefaultDatabaseProperties;
import com.vaskojovanoski.multitenancy_example.configuration.database.properties.Tenant1DatabaseProperties;
import com.vaskojovanoski.multitenancy_example.configuration.database.properties.Tenant2DatabaseProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class DataSourceRouting {
    private static final String PSQL_DRIVER = "org.postgresql.Driver";
    private final DefaultDatabaseProperties defaultDatabaseProperties;
    private final Tenant1DatabaseProperties tenant1DatabaseProperties;
    private final Tenant2DatabaseProperties tenant2DatabaseProperties;

    @Bean
    public DataSource dataSource() {
        final AbstractRoutingDataSource multiTenantDataSource = new MultiTenantDataSource();

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("default", defaultDataSource());
        targetDataSources.put("tenant1", tenant1DataSource());
        targetDataSources.put("tenant2", tenant2DataSource());

        multiTenantDataSource.setTargetDataSources(targetDataSources);
        multiTenantDataSource.setDefaultTargetDataSource(defaultDataSource());

        return multiTenantDataSource;
    }

    public DataSource defaultDataSource() {
        final DataSourceBuilder dataSource = DataSourceBuilder.create();
        dataSource.url(defaultDatabaseProperties.getUrl());
        dataSource.username(defaultDatabaseProperties.getUsername());
        dataSource.password(defaultDatabaseProperties.getPassword());
        dataSource.driverClassName(PSQL_DRIVER);
        return dataSource.build();
    }

    public DataSource tenant1DataSource() {
        final DataSourceBuilder dataSource = DataSourceBuilder.create();
        dataSource.url(tenant1DatabaseProperties.getUrl());
        dataSource.username(tenant1DatabaseProperties.getUsername());
        dataSource.password(tenant1DatabaseProperties.getPassword());
        dataSource.driverClassName(PSQL_DRIVER);
        return dataSource.build();
    }

    public DataSource tenant2DataSource() {
        final DataSourceBuilder dataSource = DataSourceBuilder.create();
        dataSource.url(tenant2DatabaseProperties.getUrl());
        dataSource.username(tenant2DatabaseProperties.getUsername());
        dataSource.password(tenant2DatabaseProperties.getPassword());
        dataSource.driverClassName(PSQL_DRIVER);
        return dataSource.build();
    }
}