package com.example;

import com.example.controller.CustomerController;
import com.example.model.Customer;
import com.example.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void testAddCustomer() throws Exception {
        Customer customer = new Customer();
        // Set customer details

        mockMvc.perform(MockMvcRequestBuilders.post("/customers/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteCustomer() throws Exception {
        Long customerId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/customers/delete/{customerId}", customerId))
                .andExpect(status().isOk());
    }

    @Test
    void testListCustomers() throws Exception {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer());
        when(customerService.listCustomers()).thenReturn(customers);

        mockMvc.perform(MockMvcRequestBuilders.get("/customers/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    // Other test methods for remaining controller endpoints

    // Utility method to convert object to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
