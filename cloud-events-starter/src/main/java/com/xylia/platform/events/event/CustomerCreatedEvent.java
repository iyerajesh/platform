package com.xylia.platform.events.event;

import com.xylia.platform.events.model.Address;
import com.xylia.platform.events.model.Contact;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class CustomerCreatedEvent {

    @Id
    private String id;

    private String name;

    private Contact contact;

    private List<Address> addresses;
}
