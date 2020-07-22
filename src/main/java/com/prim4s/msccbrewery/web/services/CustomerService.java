package com.prim4s.msccbrewery.web.services;

import com.prim4s.msccbrewery.web.model.CustomerDTO;

import java.util.UUID;

/**
 * Created by CHAMGOUE on 21/07/2020
 */
public interface CustomerService {
    CustomerDTO getCustomerById(UUID beerId);
}
