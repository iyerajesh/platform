package com.xylia.platform.events.test;

import com.xylia.platform.events.builder.CloudEventImpl;
import com.xylia.platform.events.builder.CloudEventBuilder;
import com.xylia.platform.events.event.CustomerCreatedEvent;
import io.cloudevents.extensions.DistributedTracingExtension;
import io.cloudevents.extensions.ExtensionFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CloudEventController {

    private final String eventType = "com.xylia.domain.customer.events.CustomerCreatedEvent";

    @GetMapping(path = "/emit")
    public ResponseEntity<String> emitCloudEvent() {

        final CustomerCreatedEvent payload = CustomerCreatedEventBuilder.createSampleCustomerCreatedEvent();

        // add trace extension usin the in-memory format
        final DistributedTracingExtension dt = new DistributedTracingExtension();
        dt.setTraceparent("00-4bf92f3577b34da6a3ce929d0e0e4736-00f067aa0ba902b7-01");
        dt.setTracestate("rojo=00-4bf92f3577b34da6a3ce929d0e0e4736-00f067aa0ba902b7-01");

        final ExtensionFormat tracing = new DistributedTracingExtension.Format(dt);

        final CloudEventImpl cloudEvent = CloudEventBuilder.builder()
                .withType(eventType)
                .withData(payload)
                .withExtension(tracing)
                .withExtension(tracing)
                .build();


//        final CloudEvent<CustomerCreatedEvent> cloudEvent = new CloudEventsBuilder<CustomerCreatedEvent>()
//                .type(eventType)
//                .data(payload)
//                .build();

        return new ResponseEntity(cloudEvent, HttpStatus.CREATED);
    }
}
