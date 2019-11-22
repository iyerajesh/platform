package com.xylia.domain.orders.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xylia.domain.orders.util.Json;
import io.cloudevents.v03.CloudEventImpl;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class CloudEventKafkaDeserializer<T> implements Deserializer<CloudEventImpl> {

    private ObjectMapper mapper;

    @Override
    public void close() {
    }

    @Override
    public void configure(final Map<String, ?> settings, final boolean isKey) {
        mapper = new ObjectMapper();
    }

    @Override
    public CloudEventImpl deserialize(final String topic, final byte[] payload) {
        return Json.binaryDecodeValue(payload, CloudEventImpl.class);
    }
}