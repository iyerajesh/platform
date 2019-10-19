package com.xylia.domain.customer.controller;

import com.xylia.domain.customer.repository.CustomerRepository;
import com.xylia.domain.customer.util.SampleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.xylia.domain.customer.util.SampleData.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/sample")
    public ResponseEntity<String> sampleData() {

        customerRepository.deleteAll();
        customerRepository.saveAll(createSampleCustomers());

        return new ResponseEntity("Sample customer accounts created", HttpStatus.CREATED);
    }
}
