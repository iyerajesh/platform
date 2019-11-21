package com.xylia.domain.orders.config;

import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Map;
import java.util.function.Supplier;

/**
 * a simple Kafka Consumer factory that builds a KafkaConsumer instance from a {@link Map} as
 * the properties to configure it.
 *
 * @param <K> the type of the key of the Kafka record
 * @param <V> the type of the value of the Kafka record
 */

public class CloudEventsKafkaConsumerFactory<K, V> extends DefaultKafkaConsumerFactory<K, V> {

        private static final long serialVersionUID = -2346087278604915148L;
        private Map<String, Object> config;


    public CloudEventsKafkaConsumerFactory(Map<String, Object> configs) {
        super(configs);
    }

    public CloudEventsKafkaConsumerFactory(Map<String, Object> configs, Deserializer<K> keyDeserializer, Deserializer<V> valueDeserializer) {
        super(configs, keyDeserializer, valueDeserializer);
    }

    public CloudEventsKafkaConsumerFactory(Map<String, Object> configs, Supplier<Deserializer<K>> keyDeserializerSupplier, Supplier<Deserializer<V>> valueDeserializerSupplier) {
        super(configs, keyDeserializerSupplier, valueDeserializerSupplier);
    }
}
