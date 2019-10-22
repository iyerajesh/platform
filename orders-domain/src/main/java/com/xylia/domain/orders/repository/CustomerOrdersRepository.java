package com.xylia.domain.orders.repository;

import com.xylia.domain.orders.model.CustomerOrders;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerOrdersRepository extends MongoRepository<CustomerOrders, String> {
}
