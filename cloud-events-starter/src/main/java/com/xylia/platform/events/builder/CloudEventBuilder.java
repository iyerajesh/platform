package com.xylia.platform.events.builder;

import io.cloudevents.extensions.ExtensionFormat;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Slf4j
public final class CloudEventBuilder<T> implements EventBuilder<T, AttributesImpl> {

    private static Validator VALIDATOR;

    public static final String SPEC_VERSION = "0.3";
    private static final String MESSAGE_SEPARATOR = ", ";
    private static final String MESSAGE = "'%s' %s";
    private static final String ERR_MESSAGE = "invalid payload: %s";

    private String id = UUID.randomUUID()
            .toString();

    private URI source = URI.create("platform");

    private String type;

    private ZonedDateTime time = ZonedDateTime.now();
    private URI schemaurl;
    private String datacontentencoding;
    private String datacontenttype;
    private String subject;

    private T data;

    private final Set<ExtensionFormat> extensions = new HashSet<>();
    private Validator validator;

    private CloudEventBuilder(Validator validator) {
        if (validator == null)
            this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    private static Validator getValidator() {
        if (null == VALIDATOR) {
            VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
        }
        return VALIDATOR;
    }

    public static <T> CloudEventBuilder<T> builder() {
        return new CloudEventBuilder<T>(null);
    }

    public CloudEventBuilder<T> withId(String id) {
        this.id = id;
        return this;
    }

    public CloudEventBuilder<T> withSource(URI source) {
        this.source = source;
        return this;
    }

    public CloudEventBuilder<T> withType(String type) {
        this.type = type;
        return this;
    }

    public CloudEventBuilder<T> withTime(ZonedDateTime time) {
        this.time = time;
        return this;
    }

    public CloudEventBuilder<T> withSchemaurl(URI schemaurl) {
        this.schemaurl = schemaurl;
        return this;
    }

    public CloudEventBuilder<T> withDatacontentencoding(
            String datacontentencoding) {
        this.datacontentencoding = datacontentencoding;
        return this;
    }

    public CloudEventBuilder<T> withDatacontenttype(
            String datacontenttype) {
        this.datacontenttype = datacontenttype;
        return this;
    }

    public CloudEventBuilder<T> withSubject(
            String subject) {
        this.subject = subject;
        return this;
    }

    public CloudEventBuilder<T> withData(T data) {
        this.data = data;
        return this;
    }

    public CloudEventBuilder<T> withExtension(ExtensionFormat extension) {
        this.extensions.add(extension);
        return this;
    }

    public CloudEventImpl<T> build() {

        AttributesImpl attributes = new AttributesImpl(id, source, SPEC_VERSION,
                type, time, schemaurl, datacontentencoding, datacontenttype,
                subject);

        CloudEventImpl<T> cloudEvent = new CloudEventImpl<T>(attributes, data, extensions);

        Set<ConstraintViolation<Object>> violations = validator.validate(cloudEvent);
        violations.addAll(validator.validate(cloudEvent.getAttributes()));

        final String errors = violations.stream().
                map(v -> format(MESSAGE, v.getPropertyPath(), v.getMessage()))
                .collect(Collectors.joining(MESSAGE_SEPARATOR));

        log.info("Errors from the validation: {}", errors);

        Optional.ofNullable(
                "".equals(errors) ? null : errors

        ).ifPresent((e) -> {
            throw new IllegalStateException(format(ERR_MESSAGE, e));
        });

        return cloudEvent;
    }
}

