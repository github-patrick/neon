package com.neon.banking.service;

import com.neon.banking.dto.CustomerDto;
import com.neon.banking.exceptions.CustomerNotFoundException;
import com.neon.banking.exceptions.ModelConstraintErrorException;
import com.neon.banking.exceptions.UsernameExistsException;
import com.neon.banking.mapper.CustomerMapper;
import com.neon.banking.model.Customer;
import com.neon.banking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    private CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerDto createCustomer(Customer customer) {

        Customer customerSaved = customerRepository.save(customer);
        CustomerDto customerDtoSaved = customerMapper.map(customerSaved);
        return customerDtoSaved;
    }

    public List<CustomerDto> getCustomers() {
        List<CustomerDto> customers = new ArrayList<>();
        Iterator<Customer> iterator = customerRepository.findAll().iterator();

        while(iterator.hasNext()) {

            customers.add(customerMapper.map(iterator.next()));
        }
        return customers;
    }

    public CustomerDto getCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            return null;
        }
        CustomerDto customerDto = customerMapper.map(customer.get());
        return customerDto;
    }


    public void deleteManager(CustomerDto customerDto) {
        Customer customer = customerMapper.map(customerDto);
        customerRepository.delete(customer);
    }

    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void checkIfUsernameIsUnique(String username) {
        if (customerRepository.findByUsernameIgnoreCase(username) != null) {
            throw new UsernameExistsException("Username exists");
        }
    }

    public void checkIfUsernameIsUnique(Customer customer) {

        if (getCustomer(customer.getId()).getUsername().equalsIgnoreCase(customer.getUsername())) {
            return;
        }
        if (customerRepository.findByUsernameIgnoreCase(customer.getUsername()) != null) {
            throw new UsernameExistsException("Username exists");
        }
    }

    public void handleModelConstraints(String errorMessage) {
        throw new ModelConstraintErrorException(errorMessage);
    }

    public void checkCustomerExists(Long id) {
        if (!customerRepository.findById(id).isPresent()) {
            throw new CustomerNotFoundException("customer of id " + id + " does not exist");
        }
    }
}
