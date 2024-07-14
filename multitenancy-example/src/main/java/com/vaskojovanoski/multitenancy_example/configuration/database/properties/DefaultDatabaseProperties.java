package com.vaskojovanoski.multitenancy_example.configuration.database.properties;

import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "spring.datasource.default")
@Value
@Log4j2
public class DefaultDatabaseProperties {

    String url;
    String username;
    String password;

    @ConstructorBinding
    public DefaultDatabaseProperties(final String url,
                                     final String username,
                                     final String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
}



