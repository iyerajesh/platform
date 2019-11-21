package com.xylia.domain.orders.consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xylia.domain.orders.model.CustomerOrders;
import com.xylia.domain.orders.repository.CustomerOrdersRepository;
import com.xylia.domain.orders.util.Json;
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

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "${spring.kafka.topic.accounts-customer}")
    public void receiveCustomerOrder(String payload) throws Exception {

        log.info("cloud event payload='{}'", payload);

        CloudEventImpl cloudEvent = Json.readValue(payload);
        CustomerOrders customerOrders =
                Json.MAPPER.convertValue(cloudEvent.getData().get(),
                        new TypeReference<CustomerOrders>() {
                        });

//        data.forEach((key, value) -> {
//            log.info(key + " -> " + value);
//        });

        log.info("customer orders: {}", customerOrders);
        customerOrdersRepository.save(customerOrders);
    }


    @KafkaListener(topics = "${spring.kafka.topic.accounts-customer}.DLT")
    public void dltWrite(String data) {

        log.info("DLT write='{}'", data);
    }


}