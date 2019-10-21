package com.xylia.domain.accounts.repository;

import com.xylia.domain.accounts.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
