package com.xylia.domain.accounts.config;

import com.xylia.domain.accounts.model.CustomerChangeEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

public interface SenderConfig {

    @Bean
    Map<String, Object> producerConfigs();

    @Bean
    ProducerFactory<String, CustomerChangeEvent> producerFactory();

    @Bean
    KafkaTemplate<String, CustomerChangeEvent> kafkaTemplate();

}
