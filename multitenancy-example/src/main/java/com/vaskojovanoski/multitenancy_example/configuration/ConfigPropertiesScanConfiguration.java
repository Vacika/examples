package com.vaskojovanoski.multitenancy_example.configuration;

import com.vaskojovanoski.multitenancy_example.configuration.database.properties.DefaultDatabaseProperties;
import com.vaskojovanoski.multitenancy_example.configuration.database.properties.Tenant1DatabaseProperties;
import com.vaskojovanoski.multitenancy_example.configuration.database.properties.Tenant2DatabaseProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties
@ConfigurationPropertiesScan(basePackageClasses = {DefaultDatabaseProperties.class, Tenant1DatabaseProperties.class, Tenant2DatabaseProperties.class})
public class ConfigPropertiesScanConfiguration {
}
