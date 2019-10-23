package com.xylia.domain.orders.consumer;

import com.xylia.domain.orders.model.CustomerOrders;
import com.xylia.domain.orders.repository.CustomerOrdersRepository;
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

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "${spring.kafka.topic.accounts-customer}")
    public void receiveCustomerOrder(CustomerOrders customerOrders) {

        log.info("received payload='{}'", customerOrders);
//        latch.countDown();
        customerOrdersRepository.save(customerOrders);
    }

}