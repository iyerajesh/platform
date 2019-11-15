package com.xylia.domain.orders.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xylia.domain.orders.model.CustomerOrders;
import com.xylia.domain.orders.repository.CustomerOrdersRepository;
import io.cloudevents.v03.CloudEventImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
public class KafkaConsumer {

    @Autowired
    private CustomerOrdersRepository customerOrdersRepository;
    private CountDownLatch latch = new CountDownLatch(1);
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "${spring.kafka.topic.accounts-customer}")
    public void receiveCustomerOrder(CustomerOrders customerOrders) throws Exception {

//        log.info("received cloud event payload='{}'", cloudEvent);
//
//        CustomerOrders customerOrders = objectMapper
//                .readValue(cloudEvent.getData().get(), CustomerOrders.class);

        log.info("customer order payload='{}'", customerOrders);

        customerOrdersRepository.save(customerOrders);
    }

}