package com.xylia.domain.customer.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CommandConfiguration {

    @Bean
    public AggregateLoader aggregateLoader(ApplicationContext applicationContext) {
        return new AggregateLoader(applicationContext);
    }
}
