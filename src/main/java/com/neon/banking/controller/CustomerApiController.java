package com.neon.banking.controller;

import com.neon.banking.dto.CustomerDto;
import com.neon.banking.model.Customer;
import com.neon.banking.model.Manager;
import com.neon.banking.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(CustomerApiController.RESOURCE_PATH)
public class CustomerApiController {

    public static final String RESOURCE_PATH = "/customers";

    private CustomerService customerService;

    public CustomerApiController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody @Valid Customer customer) {

        customerService.checkIfUsernameIsUnique(customer.getUsername());


        return new ResponseEntity(customerService.createCustomer(customer), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDto>> getCustomers() {

        return new ResponseEntity(customerService.getCustomers(),HttpStatus.OK);
    }

    @GetMapping(value= "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long id) {

        CustomerDto customerDto = customerService.getCustomer(id);

        if (customerDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customerDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteManager(@PathVariable Long id) {

        CustomerDto customerDto = customerService.getCustomer(id);

        if (customerDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.deleteManager(customerDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity updateManager(@RequestBody @Valid Customer customer, @PathVariable Long id) {

        if (customerService.getCustomer(id) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        customer.setId(id);
        customerService.updateCustomer(customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
