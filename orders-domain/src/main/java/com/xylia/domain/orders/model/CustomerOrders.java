package com.xylia.domain.orders.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "customer.orders")
public class CustomerOrders {

    @Id
    private String id;

    @NonNull
    private Name name;

    @NonNull
    private Contact contact;

    @NonNull
    private List<Address> addresses;

    @NonNull
    private List<Order> orders;
}
