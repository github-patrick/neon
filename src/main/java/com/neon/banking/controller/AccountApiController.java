package com.neon.banking.controller;

import com.neon.banking.dto.AccountDto;
import com.neon.banking.dto.CustomerDto;
import com.neon.banking.mapper.AccountMapper;
import com.neon.banking.model.Account;
import com.neon.banking.service.AccountService;
import com.neon.banking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(AccountApiController.RESOURCE_PATH)
public class AccountApiController {

    public static final String RESOURCE_PATH = "/customers/{customerId}/accounts";

    private final AccountService accountService;
    private final CustomerService customerService;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    public AccountApiController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> createAccount(@RequestBody @Valid Account account, BindingResult result,
                                                    @PathVariable final Long customerId) {
        customerService.checkCustomerExists(customerId);

        if (result.hasErrors()) {
            accountService.handleModelConstraints(result.getFieldError().getDefaultMessage());
        }

        return new ResponseEntity(accountService.createAccount(account, customerId), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountDto>> getAccounts(@PathVariable Long customerId) {
        customerService.checkCustomerExists(customerId);

        return new ResponseEntity(accountService.getAccounts(customerId),HttpStatus.OK);
    }

    @GetMapping(value= "{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> getAccount(@PathVariable Long customerId, @PathVariable Long accountId) {

        customerService.checkCustomerExists(customerId);
        accountService.checkAccountExists(customerId, accountId);

        AccountDto accountDto = accountService.getAccount(customerId,accountId);
        return new ResponseEntity(accountDto, HttpStatus.OK);
    }

    @PatchMapping(path = "{accountId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity patchStudent(@RequestBody Account account, @PathVariable Long customerId, @PathVariable
            Long accountId) {

        accountService.checkAccountExists(customerId, accountId);

        accountService.patchAccount(account, accountId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }




}
