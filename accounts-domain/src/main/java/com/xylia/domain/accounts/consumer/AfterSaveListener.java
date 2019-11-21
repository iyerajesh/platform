package com.xylia.domain.accounts.consumer;

import com.xylia.domain.accounts.model.Customer;
import com.xylia.domain.accounts.model.CustomerChangeEvent;
import com.xylia.domain.accounts.producer.Producer;
import io.cloudevents.extensions.DistributedTracingExtension;
import io.cloudevents.extensions.ExtensionFormat;
import io.cloudevents.v03.CloudEventBuilder;
import io.cloudevents.v03.CloudEventImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Controller;

import java.net.URI;
import java.util.UUID;

@Controller
@Slf4j
public class AfterSaveListener extends AbstractMongoEventListener<Customer> {

    @Value("${spring.kafka.topic.accounts-customer}")
    private String topic;

    @Autowired
    private Producer producer;

    private final String eventType = "com.xylia.domain.customer.events.CustomerCreatedEvent";

    public void onAfterSave(AfterSaveEvent<Customer> event) {

        log.info("onAfterSave event='{}'", event);
        Customer customer = event.getSource();


        CustomerChangeEvent eventBody = CustomerChangeEvent.builder()
                .name(customer.getName())
                .id(customer.getId())
                .addressList(customer.getAddresses())
                .contact(customer.getContact())
                .build();

        // add trace extension usin the in-memory format
        final DistributedTracingExtension dt = new DistributedTracingExtension();
        dt.setTraceparent("00-4bf92f3577b34da6a3ce929d0e0e4736-00f067aa0ba902b7-01");
        dt.setTracestate("rojo=00-4bf92f3577b34da6a3ce929d0e0e4736-00f067aa0ba902b7-01");

        final ExtensionFormat tracing = new DistributedTracingExtension.Format(dt);

        final CloudEventImpl<Object> payload = CloudEventBuilder.builder()
                .withId(UUID.randomUUID().toString())
                .withType(eventType)
                .withSource(URI.create("/accounts"))
                .withData(eventBody)
                .withExtension(tracing)
                .withExtension(tracing)
                .build();


        producer.send(topic, payload);
    }

}
