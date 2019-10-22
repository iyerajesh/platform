package com.xylia.domain.orders.model;

import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

@Data
public class Order {

    @NonNull
    private String guid;

    @NonNull
    private List<OrderItem> orderItems;

    public Order() {

        this.guid = UUID.randomUUID().toString();
    }

    public Order(List<OrderItem> orderItems) {

        this.guid = UUID.randomUUID().toString();
        this.orderItems = orderItems;
    }
}
