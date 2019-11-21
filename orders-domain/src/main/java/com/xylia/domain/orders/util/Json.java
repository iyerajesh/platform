package com.xylia.domain.orders.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import io.cloudevents.v03.CloudEventImpl;

import java.time.ZonedDateTime;

public final class Json {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.registerModule(new Jdk8Module());

        final SimpleModule module = new SimpleModule();
        module.addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer());
        MAPPER.registerModule(module);
    }

    /**
     * Encode a POJO to JSON using the underlying Jackson mapper.
     *
     * @param obj a POJO
     * @return a String containing the JSON representation of the given POJO.
     * @throws IllegalStateException if a property cannot be encoded.
     */
    public static String encode(final Object obj) throws IllegalStateException {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to encode as JSON: " + e.getMessage());
        }
    }

    /**
     * Returns a CloudEvent POJO using the underlying Jackson mapper.
     *
     * @param payload The cloudevent payload string.
     * @return a CloudEvent object containing the object representation of the given string.
     * @throws JsonProcessingException if the payload cannot be processed.
     */
    public static CloudEventImpl readValue(String payload) throws JsonProcessingException {
        return MAPPER.readValue(payload, CloudEventImpl.class);
    }

    private Json() {
        // no-op
    }
}
