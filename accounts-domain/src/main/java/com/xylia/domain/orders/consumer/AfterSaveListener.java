package com.xylia.domain.orders.consumer;

import com.xylia.domain.orders.model.Customer;
import com.xylia.domain.orders.model.CustomerChangeEvent;
import com.xylia.domain.orders.producer.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class AfterSaveListener extends AbstractMongoEventListener<Customer> {

    @Value("${spring.kafka.topic.accounts-customer}")
    private String topic;

    @Autowired
    private Producer producer;

    public void onAfterSave(AfterSaveEvent<Customer> event) {

        log.info("onAfterSave event='{}'", event);
        Customer customer = event.getSource();

        CustomerChangeEvent payload = CustomerChangeEvent.builder()
                .name(customer.getName())
                .id(customer.getId())
                .addressList(customer.getAddresses())
                .contact(customer.getContact())
                .build();

        producer.send(topic, payload);
    }

}
