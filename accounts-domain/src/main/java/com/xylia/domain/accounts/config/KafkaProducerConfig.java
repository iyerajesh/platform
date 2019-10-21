package com.xylia.domain.accounts.config;

import com.xylia.domain.accounts.model.CustomerChangeEvent;
import com.xylia.domain.accounts.producer.Producer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

public interface KafkaProducerConfig {

    Map<String, Object> producerConfigs();

    ProducerFactory<String, CustomerChangeEvent> producerFactory();

    KafkaTemplate<String, CustomerChangeEvent> kafkaTemplate();

}
