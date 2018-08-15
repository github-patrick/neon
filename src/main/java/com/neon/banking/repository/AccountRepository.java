package com.neon.banking.repository;

import com.neon.banking.model.Account;
import com.neon.banking.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Iterable<Account> findByCustomerId(Long customerId);

    Optional<Account> findByCustomerAndId(Customer customer, Long AccountId);

}
