package com.neon.banking.service;

import com.neon.banking.dto.AccountDto;
import com.neon.banking.exceptions.AccountNotFoundException;
import com.neon.banking.exceptions.ModelConstraintErrorException;
import com.neon.banking.mapper.AccountMapper;
import com.neon.banking.model.Account;
import com.neon.banking.model.Customer;
import com.neon.banking.repository.AccountRepository;
import com.neon.banking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public AccountDto createAccount (Account account, Long customerId) {
        account.setCustomer(customerRepository.findById(customerId).get());
        Account accountSaved = accountRepository.save(account);
        return accountMapper.map(accountSaved);

    }

    public List<AccountDto> getAccounts(Long customerId) {
        List<AccountDto> accounts = new ArrayList<>();

        Iterator<Account> iterator = accountRepository.findByCustomerId(customerId).iterator();
        while (iterator.hasNext()) {
            accounts.add(accountMapper.map(iterator.next()));
        }
        return accounts;
    }

    public AccountDto getAccount(Long customerId, Long accountId) {
        Account account = accountRepository.findByCustomerAndId(customerRepository.findById(customerId).get(), accountId).get();
        return accountMapper.map(account);
    }

    public void handleModelConstraints(String errorMessage) {
        throw new ModelConstraintErrorException(errorMessage);
    }

    public void checkAccountExists(Long customerId, Long accountId) {

        Customer customer = customerRepository.findById(customerId).get();

        if (!accountRepository.findByCustomerAndId(customer, accountId).isPresent()) {
            throw new AccountNotFoundException("account of id " + accountId + " does not exist for customer with the id "
                     + customerId);
        }
    }

    public void patchAccount(Account account, Long id) {

        Account accountRetrieved = accountRepository.findById(id).get();

        Account accountPatched = new Account();

        accountPatched.setId(accountRetrieved.getId());

        if (account.getBalance() == null) {
            accountPatched.setBalance(accountRetrieved.getBalance());
        }
        if (account.getCustomer() == null) {
            accountPatched.setCustomer(accountRetrieved.getCustomer());
        }
        if (account.getTransactions() == null) {
            accountPatched.setTransactions(accountRetrieved.getTransactions());
        }
        if (account.getCreatedDate() == null) {
            accountPatched.setCreatedDate(accountRetrieved.getCreatedDate());
        }
        if (account.getUpdatedDate() == null) {
            accountPatched.setUpdatedDate(accountRetrieved.getUpdatedDate());
        }

        accountRepository.save(accountPatched);
    }
}
