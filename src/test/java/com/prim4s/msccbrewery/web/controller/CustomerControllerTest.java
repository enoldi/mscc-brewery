package com.prim4s.msccbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prim4s.msccbrewery.web.controller.v1.CustomerController;
import com.prim4s.msccbrewery.web.model.CustomerDTO;
import com.prim4s.msccbrewery.web.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by CHAMGOUE on 22/07/2020
 */

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    CustomerDTO validCustomer;

    @BeforeEach
    void setUp() {
        validCustomer = CustomerDTO.builder().id(UUID.randomUUID())
                .customerName("ATANGANA")
                .beerQuantities(14L)
                .build();
    }

    @Test
    void getBeerDto() throws Exception {
        given(customerService.getCustomerById(any(UUID.class))).willReturn(validCustomer);

        mockMvc.perform(get("/api/v1/customer/" + validCustomer.getId().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validCustomer.getId().toString())))
                .andExpect(jsonPath("$.customerName", is("ATANGANA")))
                .andExpect(jsonPath("$.beerQuantities", is(14)));

    }

    @Test
    void postCustomer() throws Exception {
        // given
        CustomerDTO customerDTO = validCustomer;
        customerDTO.setId(null);
        CustomerDTO customerDTOSaved = CustomerDTO.builder().id(UUID.randomUUID())
                .customerName("OYONO")
                .beerQuantities(1L)
                .build();

        String customerDtoJson = objectMapper.writeValueAsString(customerDTO);

        given(customerService.saveNewCustomer(any())).willReturn(customerDTOSaved);
        mockMvc.perform(post("/api/v1/customer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerDtoJson))
                        .andExpect(status().isCreated());
    }

    @Test
    void updateCustomer() throws Exception {
        //given
        CustomerDTO customerDTO = validCustomer;
        String customerJson = objectMapper.writeValueAsString(customerDTO);

        // when
        mockMvc.perform(put("/api/v1/customer/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson))
                .andExpect(status().isNoContent());

        then(customerService).should().updateCustomer(any(), any());
    }

    @Test
    void deleteCustomer() throws Exception {
        given(customerService.getCustomerById(any(UUID.class))).willReturn(validCustomer);

        mockMvc.perform(delete("/api/v1/customer/" + validCustomer.getId().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
