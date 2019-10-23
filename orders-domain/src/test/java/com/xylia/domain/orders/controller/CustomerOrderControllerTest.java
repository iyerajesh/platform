package com.xylia.domain.orders.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerOrderControllerTest {

    public static final String ACTUATOR_HEALTH = "/actuator/health";
    public static final String ACTUATOR_INFO = "/actuator/info";
    public static final String ORDERS_LIST = "/orders/list";
    public static final String ORDERS_DETAILS = "/orders/order/";

    private static final int CUSTOMER_ID = 99999;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenHealthStatusRequest_thenStatusIsOK() throws Exception {
        this.mockMvc.perform(get(ACTUATOR_HEALTH))
                .andExpect(status().isOk());
    }

    @Test
    public void whenInfoStatusRequest_thenStatusIsOK() throws Exception {
        this.mockMvc.perform(get(ACTUATOR_INFO))
                .andExpect(status().isOk());
    }

    @Test
    public void whenOrdersListRequest_thenStatusIsOK() throws Exception {
        this.mockMvc.perform(get(ORDERS_LIST))
                .andExpect(status().isOk());
    }

}
