package io.xylia.domain.accounts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.xylia.domain.accounts.util.SampleData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    public static final String ACTUATOR_HEALTH = "/actuator/health";
    public static final String ACTUATOR_INFO = "/actuator/info";
    public static final String ACCOUNTS_CUSTOMER_CREATE = "/accounts/customer/create";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
    public void whenNewCustomerRequest_thenStatusIsCreated() throws Exception {

        String json = objectMapper.writeValueAsString(SampleData.
                createSampleCustomers().stream()
                .findFirst().get());

        this.mockMvc.perform(post(ACCOUNTS_CUSTOMER_CREATE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
