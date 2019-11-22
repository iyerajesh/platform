package com.xylia.domain.accounts.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloudevents.json.Json;
import io.cloudevents.v03.CloudEventImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

@Slf4j
public class CloudEventKafkaSerializer implements Serializer<CloudEventImpl> {

    private ObjectMapper MAPPER;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, CloudEventImpl cloudEvent) {

        byte[] serializedBytes = null;
        try {
            serializedBytes = Json.binaryEncode(cloudEvent);
        } catch (Exception e) {
            log.error("Could not serialize CloudEvent payload: {}", e.getMessage());
        }
        return serializedBytes;
    }

    @Override
    public void close() {

    }
}