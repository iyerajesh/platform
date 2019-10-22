package com.xylia.domain.orders.controller;

import com.xylia.domain.orders.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.xylia.domain.orders.util.SampleData.*;

@RestController
@RequestMapping("/accounts")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer/create")
    public ResponseEntity<String> sampleData() {

        customerRepository.deleteAll();
        customerRepository.saveAll(createSampleCustomers());

        return new ResponseEntity("Customer account created!", HttpStatus.CREATED);
    }
}
