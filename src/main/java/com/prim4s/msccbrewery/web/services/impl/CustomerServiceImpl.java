package com.prim4s.msccbrewery.web.services.impl;

import com.prim4s.msccbrewery.web.model.BeerDto;
import com.prim4s.msccbrewery.web.model.CustomerDTO;
import com.prim4s.msccbrewery.web.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by CHAMGOUE on 21/07/2020
 */

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDTO getCustomerById(UUID beerId) {
        return CustomerDTO.builder().id(UUID.randomUUID())
                .CustomerName("ANDRE")
                .beerQuantities(3L)
                .build();
    }
}
