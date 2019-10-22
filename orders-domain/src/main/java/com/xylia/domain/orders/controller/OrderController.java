package com.xylia.domain.orders.controller;

import com.xylia.domain.orders.repository.CustomerOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private CustomerOrdersRepository customerOrdersRepository;

}
