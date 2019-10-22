package com.xylia.domain.orders.repository;

import com.xylia.domain.orders.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
