package com.prim4s.msccbrewery.web.controller.v1;

import com.prim4s.msccbrewery.web.model.CustomerDTO;
import com.prim4s.msccbrewery.web.services.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import javax.validation.Valid;

/**
 * Created by CHAMGOUE on 21/07/2020
 */

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     *  get customer by id
     * @param customerId
     * @return responseEntity CustomerDto
     */
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getBeerDto(@PathVariable("customerId") UUID customerId) {

        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    /**
     * post new customer
     * @param customerDTO
     * @return responseEntity CustomerDto
     */
    @PostMapping
    public ResponseEntity postCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO customerDTOSaved = customerService.saveNewCustomer(customerDTO);

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
        customerService.updateCustomer(customerId, customerDTO);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("customerId") UUID customerId) {
        customerService.deleteById(customerId);
    }
}
