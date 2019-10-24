package com.xylia.domain.orders.controller;

import com.xylia.domain.orders.model.CustomerOrders;
import com.xylia.domain.orders.repository.CustomerOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/orders")
public class CustomerOrderController {

    @Autowired
    private CustomerOrdersRepository customerOrdersRepository;

    @GetMapping("list")
    public ResponseEntity<List<CustomerOrders>> getAllBooks() {
        return ResponseEntity.ok(customerOrdersRepository.findAll());
    }

    @GetMapping("/order/{customerId}")
    public ResponseEntity<?> getCustomerOrderById(@PathVariable final String customerId) {

        Optional<CustomerOrders> customerOrders = customerOrdersRepository.findById(customerId);
        if (customerOrders.isPresent())
            return ResponseEntity.ok(customerOrders.get());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Customer not found!");
    }
}
