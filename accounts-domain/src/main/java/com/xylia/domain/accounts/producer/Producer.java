package com.xylia.domain.accounts.producer;

import io.cloudevents.kafka.CloudEventsKafkaProducer;
import io.cloudevents.v03.CloudEventImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.CorruptRecordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Producer {

    @Autowired
    private CloudEventsKafkaProducer ceProducer;

    public void send(String topic, CloudEventImpl<String> cePayload) {

        try {
            log.info("sending CloudEvent payload='{}' to topic='{}'", cePayload, topic);

            ceProducer.send(new ProducerRecord<>(topic, cePayload));
            log.info("completed payload sent!");

        } catch (CorruptRecordException e) {
            log.error("Exception while writing to Kafka: {}", e.getMessage());
        }

    }
}
