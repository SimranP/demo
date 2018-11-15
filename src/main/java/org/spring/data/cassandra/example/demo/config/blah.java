package org.spring.data.cassandra.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class blah {
    int id;

    @Bean
    Integer getKeyspaceName() {
        return id;
    }

    @Bean
    String getContactPoints() {
        return "localhost";
    }

}
