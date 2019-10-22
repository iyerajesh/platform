package com.xylia.domain.accounts.producer;

import com.xylia.domain.accounts.model.CustomerChangeEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.CorruptRecordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Producer {

    @Autowired
    private KafkaTemplate<String, CustomerChangeEvent> kafkaTemplate;

    public void send(String topic, CustomerChangeEvent payload) {

        try {
            log.info("sending payload='{}' to topic='{}'", payload, topic);
            kafkaTemplate.send(topic, payload);
            log.info("completed payload send!");
        } catch (CorruptRecordException e) {
            log.error("Exception while writing to Kafka: {}", e.getMessage());
        }

    }
}
