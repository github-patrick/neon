package com.neon.banking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neon.banking.dto.AccountDto;
import com.neon.banking.dto.CustomerDto;
import com.neon.banking.service.AccountService;
import com.neon.banking.service.CustomerService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(MockitoJUnitRunner.class)
public class AccountApiControllerTest {

    @Mock
    private AccountService accountService;

    @Mock
    private CustomerService customerService;

    private AccountApiController accountApiController;

    private MockMvc mockMvc;
    private AccountDto accountDto;
    private CustomerDto customerDto;

    @Before
    public void setUp() {
        accountApiController = new AccountApiController(accountService, customerService);
        mockMvc = MockMvcBuilders.standaloneSetup(accountApiController).build();

        accountDto = AccountDto.builder().build();
        customerDto = CustomerDto.builder().id(1l).username("testName").build();


    }

    @Test
    public void createAccount() throws Exception {
        mockMvc.perform(post(AccountApiController.RESOURCE_PATH, 1l)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(new ObjectMapper().writeValueAsString(accountDto)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void getAccounts() throws Exception {
        mockMvc.perform(get(AccountApiController.RESOURCE_PATH, 1l)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void getAccount() throws Exception {
        accountDto.setId(1l);
        when(accountService.getAccount(customerDto.getId(), accountDto.getId())).thenReturn(accountDto);

        mockMvc.perform(get(AccountApiController.RESOURCE_PATH + "/1",1l)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Ignore
    public void getAccountNotFound() throws Exception {
        //Fixme need to come up with a way to test this

        when(accountService.getAccount(2l,2l)).thenReturn(null);

        mockMvc.perform(get(AccountApiController.RESOURCE_PATH + "/2", 2l)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}