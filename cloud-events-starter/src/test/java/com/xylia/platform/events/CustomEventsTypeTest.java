package com.xylia.platform.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xylia.platform.events.builder.CloudEventBuilder;
import com.xylia.platform.events.builder.CloudEventImpl;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CustomEventsTypeTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void testBinding() throws IOException {

        // given
        final Map<String, Object> storagePayload = MAPPER.readValue(Thread.currentThread()
                .getContextClassLoader().getResourceAsStream("pvc.json"), Map.class);

        final CloudEventImpl<Object> storageCloudEvent = CloudEventBuilder.builder()
                .withType("ProvisioningSucceeded")
                .withSource(URI.create("/scheduler"))
                .withId(UUID.randomUUID().toString())
                .withData(storagePayload)
                .build();

        // when
        final String httpSerializedPayload = MAPPER.writeValueAsString(storageCloudEvent);
        assertThat(httpSerializedPayload).contains("PersistentVolumeClaim");
    }


}
