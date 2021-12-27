package com.prim4s.msccbrewery.web.services.v2.impl;

import com.prim4s.msccbrewery.web.model.CustomerDTO;
import com.prim4s.msccbrewery.web.services.v2.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by CHAMGOUE on 21/07/2020
 */

@Slf4j
@Service
public class CustomerServiceV2Impl implements CustomerService {
    @Override
    public CustomerDTO getCustomerById(UUID beerId) {
        return CustomerDTO.builder().id(UUID.randomUUID())
                .customerName("ANDRE")
                .beerQuantities(3L)
                .build();
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customerDTO) {
        return CustomerDTO.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDTO customerDTO) {
        log.debug("Updating customerDTO : {}", customerDTO);
    }

    @Override
    public void deleteById(UUID customerId) {
        log.debug("deleting customerDTO : {}", customerId);
    }
}
