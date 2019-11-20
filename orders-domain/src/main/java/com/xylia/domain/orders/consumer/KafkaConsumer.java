package com.xylia.domain.orders.consumer;

import com.xylia.domain.orders.model.CustomerOrders;
import com.xylia.domain.orders.repository.CustomerOrdersRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
public class KafkaConsumer {

    @Autowired
    private CustomerOrdersRepository customerOrdersRepository;
    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "${spring.kafka.topic.accounts-customer}")
    public void receiveCustomerOrder(CustomerOrders customerOrders) throws Exception {

        log.info("customer order payload='{}'", customerOrders);

        customerOrdersRepository.save(customerOrders);
    }


    @KafkaListener(topics = "${spring.kafka.topic.accounts-customer}.DLT")
    public void dltWrite(String data) {

        log.info("DLT write='{}'", data);
    }


}