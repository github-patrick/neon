package com.neon.banking.service;

import com.neon.banking.model.Customer;
import com.neon.banking.model.Manager;
import com.neon.banking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public List<Customer> getManagers() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    public Customer getCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            return null;
        }
        return customer.get();
    }

    public void deleteManager(Customer customer) {
        customerRepository.delete(customer);
    }

    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
