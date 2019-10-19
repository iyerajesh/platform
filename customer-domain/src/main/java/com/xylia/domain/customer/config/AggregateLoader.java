package com.xylia.domain.customer.config;

import com.xylia.domain.customer.annotations.Aggregate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AggregateLoader {

    private static final Map<String, Class> aggregateMap = new HashMap<>();
    private final ApplicationContext applicationContext;

    public AggregateLoader(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        load();
    }

    public void load() {
        applicationContext.getBeansWithAnnotation(Aggregate.class)
                .forEach(AggregateLoader::fillMap);
        log.info("List of aggregates: {}", aggregateMap);

    }

    private static void fillMap(String aggregateType, Object aggregateClass) {
        aggregateMap.put(aggregateType, aggregateClass.getClass());
    }
}
