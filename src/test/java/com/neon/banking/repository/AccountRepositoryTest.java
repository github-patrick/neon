package com.neon.banking.repository;

import com.neon.banking.dto.AccountDto;
import com.neon.banking.dto.CustomerDto;
import com.neon.banking.dto.ManagerDto;
import com.neon.banking.mapper.AccountMapper;
import com.neon.banking.mapper.CustomerMapper;
import com.neon.banking.mapper.ManagerMapper;
import com.neon.banking.model.Account;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application-test.properties")
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private AccountMapper accountMapper = AccountMapper.INSTANCE;
    private CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    @Sql("classpath:accounts-test-data.sql")
    public void shouldReturnOneAccountOfACustomer() {

        CustomerDto customerDto = customerMapper.map(customerRepository.findById(1l).get());
        AccountDto accountDto = accountMapper.map(accountRepository.findById(1l).get());

        AccountDto accountRetrieved = accountMapper.map(accountRepository.findByCustomerAndId(
                customerMapper.map(customerDto),accountDto.getId()).get());

        assertEquals(new Double(10.0) ,accountRetrieved.getBalance());
    }

    @Test
    @Sql("classpath:accounts-test-data.sql")
    public void shouldReturnAllAccountsOfACustomer() {

        CustomerDto customerDto = customerMapper.map(customerRepository.findById(1l).get());

        List<AccountDto> accounts = new ArrayList<>();
        Iterator<Account> accountsIterator = accountRepository.findByCustomerId(customerDto.getId()).iterator();

        while (accountsIterator.hasNext()) {
            accounts.add(accountMapper.map(accountsIterator.next()));
        }
        assertEquals(2, accounts.size());

    }



}
