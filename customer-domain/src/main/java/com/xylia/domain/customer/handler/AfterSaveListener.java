package com.xylia.domain.customer.handler;

import com.xylia.domain.customer.model.Customer;
import com.xylia.domain.customer.model.CustomerChangeEvent;
import com.xylia.domain.customer.producer.Producer;
import lombok.extern.log4j.Log4j2;
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
