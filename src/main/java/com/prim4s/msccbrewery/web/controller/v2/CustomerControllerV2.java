package com.prim4s.msccbrewery.web.controller.v2;

import com.prim4s.msccbrewery.web.model.CustomerDTO;
import com.prim4s.msccbrewery.web.services.v2.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Created by CHAMGOUE on 21/07/2020
 */

@RequestMapping("/api/v2/customer")
@RestController
public class CustomerControllerV2 {

    private final CustomerService customerServiceV2;

    public CustomerControllerV2(CustomerService customerService) {
        this.customerServiceV2 = customerService;
    }

    /**
     *  get customer by id
     * @param customerId
     * @return responseEntity CustomerDto
     */
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getBeerDto(@PathVariable("customerId") UUID customerId) {

        return new ResponseEntity<>(customerServiceV2.getCustomerById(customerId), HttpStatus.OK);
    }

    /**
     * post new customer
     * @param customerDTO
     * @return responseEntity CustomerDto
     */
    @PostMapping
    public ResponseEntity postCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO customerDTOSaved = customerServiceV2.saveNewCustomer(customerDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + customerDTOSaved.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    /**
     * update Customer
     * @param customerId
     * @param customerDTO
     * @return
     */
    @PutMapping("/{customerId}")
    public ResponseEntity updateCustomer(@PathVariable("customerId") UUID customerId, @Valid @RequestBody CustomerDTO customerDTO) {
        customerServiceV2.updateCustomer(customerId, customerDTO);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("customerId") UUID customerId) {
        customerServiceV2.deleteById(customerId);
    }
}
