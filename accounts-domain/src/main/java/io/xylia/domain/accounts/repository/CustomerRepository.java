package io.xylia.domain.accounts.repository;

import io.xylia.domain.accounts.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
