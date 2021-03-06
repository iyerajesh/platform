package io.xylia.domain.accounts.controller;

import io.xylia.domain.accounts.model.Customer;
import io.xylia.domain.accounts.repository.CustomerRepository;
import io.xylia.domain.accounts.util.SampleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/customer/create")
    public ResponseEntity<String> createNewAccount(@RequestBody final Customer customer) {

        customerRepository.save(customer);
        return new ResponseEntity("Customer account created!", HttpStatus.CREATED);
    }

    @GetMapping("/customer/print")
    public ResponseEntity<Customer> prettyPrint() {

        return ResponseEntity.ok(SampleData.createSampleCustomers()
                .stream().findFirst().get());
    }


}
