package com.xylia.domain.accounts.config;

import com.xylia.domain.accounts.annotations.Aggregate;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

import java.lang.reflect.Field;

@Component
@Slf4j
public class AggregateProcessor implements BeanPostProcessor {

    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }

    public Object postProcessBeforeInitialization(final Object bean, String beanName)
            throws BeansException {
        ReflectionUtils.doWithFields(bean.getClass(), new FieldCallback() {
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                if (field.getAnnotation(Aggregate.class) != null) {

                    log.info("++++++ Aggregate class name: {}", bean.getClass());
                    Logger beanLogger = LoggerFactory.getLogger(bean.getClass());
                    field.setAccessible(true);
                    field.set(bean, log);
                    field.setAccessible(false);
                }
            }
        });
        return bean;
    }
}