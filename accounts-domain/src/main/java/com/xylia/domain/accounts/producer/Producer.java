package com.xylia.domain.accounts.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xylia.domain.accounts.model.CustomerChangeEvent;
import io.cloudevents.kafka.CloudEventsKafkaProducer;
import io.cloudevents.v03.CloudEventImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.CorruptRecordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Producer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(String topic, CustomerChangeEvent customerChangeEvent) {

        try {
            log.info("sending CloudEvent payload='{}' to topic='{}'", customerChangeEvent, topic);

            ObjectMapper mapper = new ObjectMapper();
            kafkaTemplate.send(topic, mapper.writeValueAsString(customerChangeEvent));

            log.info("completed payload sent!");

        } catch (CorruptRecordException e) {
            log.error("Exception while writing to Kafka: {}", e.getMessage());
        } catch (JsonProcessingException je) {
            log.error("Exception while converting JSON to string: {}", je.getMessage());

        }

    }
}
