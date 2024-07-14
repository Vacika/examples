package com.vaskojovanoski.multitenancy_example.configuration.database.properties;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "spring.datasource.tenant.tenant1")
@Value
public class Tenant1DatabaseProperties {

    String url;
    String username;
    String password;

    @ConstructorBinding
    public Tenant1DatabaseProperties(final String url,
                                     final String username,
                                     final String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
}