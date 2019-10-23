package com.xylia.domain.accounts.controller;

import com.xylia.domain.accounts.model.Customer;
import com.xylia.domain.accounts.repository.CustomerRepository;
import com.xylia.domain.accounts.util.SampleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.xylia.domain.accounts.util.SampleData.*;

@RestController
@RequestMapping("/accounts")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/customer/create")
    public ResponseEntity<String> createNewAccount(@RequestBody Customer customer) {

        customerRepository.save(customer);
        return new ResponseEntity("Customer account created!", HttpStatus.CREATED);
    }

    @GetMapping("/customer/create")
    public ResponseEntity<String> sampleData() {

        customerRepository.deleteAll();
        customerRepository.saveAll(createSampleCustomers());

        return new ResponseEntity("Customer account created!", HttpStatus.CREATED);
    }

    @GetMapping("/customer/print")
    public ResponseEntity<Customer> prettyPrint() {

        return ResponseEntity.ok(SampleData.createSampleCustomers()
                .stream().findFirst().get());
    }


}
