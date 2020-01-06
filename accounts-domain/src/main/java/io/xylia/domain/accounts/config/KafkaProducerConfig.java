package io.xylia.domain.accounts.config;

import io.xylia.domain.accounts.model.CustomerChangeEvent;
import io.xylia.platform.events.serialization.CloudEventKafkaSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableKafka
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.properties.ssl.endpoint.identification.algorithm}")
    private String sslEndpointIdentificationAlgorithm;

    @Value("${spring.kafka.properties.sasl.mechanism}")
    private String saslMechanism;

    @Value("${spring.kafka.properties.request.timeout.ms}")
    private String requestTimeoutMs;

    @Value("${spring.kafka.properties.retry.backoff.ms}")
    private String retryBackoffMs;

    @Value("${spring.kafka.properties.security.protocol}")
    private String securityProtocol;

    @Value("${spring.kafka.properties.sasl.jaas.config}")
    private String saslJaasConfig;

    @Bean
    public Map<String, Object> producerConfigs() {

        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                CloudEventKafkaSerializer.class);

        props.put("ssl.endpoint.identification.algorithm", sslEndpointIdentificationAlgorithm);
        props.put("sasl.mechanism", saslMechanism);
        props.put("request.timeout.ms", requestTimeoutMs);
        props.put("retry.backoff.ms", retryBackoffMs);
        props.put("security.protocol", securityProtocol);
        props.put("sasl.jaas.config", saslJaasConfig);

        return props;
    }

    @Bean
    public ProducerFactory<String, CustomerChangeEvent> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, CustomerChangeEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
