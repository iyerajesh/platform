package com.xylia.domain.orders.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerChangeEvent {

    @Id
    private String id;

    @NotNull
    private Name name;

    @NotNull
    private Contact contact;

    @NotNull
    private List<Address> addressList;
}
