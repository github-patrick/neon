package com.neon.banking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neon.banking.dto.CustomerDto;
import com.neon.banking.dto.ManagerDto;
import com.neon.banking.exceptions.CustomizedResponseEntityExceptionHandler;
import com.neon.banking.model.Customer;
import com.neon.banking.model.Manager;
import com.neon.banking.repository.CustomerRepository;
import com.neon.banking.service.CustomerService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CustomerApiControllerTest {

    @Mock
    private CustomerService customerService;

    private CustomerApiController customerApiController;

    private MockMvc mockMvc;

    private CustomerDto customerDto;

    private ManagerDto managerDto;

    @Before
    public void setUp() {
        customerApiController = new CustomerApiController(customerService);
        mockMvc = MockMvcBuilders.standaloneSetup(customerApiController)
                .setControllerAdvice(new CustomizedResponseEntityExceptionHandler()).build();

        managerDto = ManagerDto.builder().id(1L).firstName("Mark").lastName("Matthew").username("markup")
                .password("password").customers(null).build();


        customerDto = CustomerDto.builder().id(1L).username("Toby").password("password").firstName("Thomas").lastName("Hardy")
                .accounts(null).build();
    }


    @Test
    public void createCustomer() throws Exception {
        mockMvc.perform(post(CustomerApiController.RESOURCE_PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(new ObjectMapper().writeValueAsString(customerDto)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Ignore
    public void CreateCustomerBadRequest() throws Exception {

        customerDto.setPassword("d");

        mockMvc.perform(post(CustomerApiController.RESOURCE_PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(new ObjectMapper().writeValueAsString(customerDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getCustomers() throws Exception {
        mockMvc.perform(get(CustomerApiController.RESOURCE_PATH)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void getCustomer() throws Exception {
        when(customerService.getCustomer(customerDto.getId())).thenReturn(customerDto);

        mockMvc.perform(get(CustomerApiController.RESOURCE_PATH + "/1")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void getCustomerNotFound() throws Exception {
        when(customerService.getCustomer(new Long(2))).thenReturn(null);

        mockMvc.perform(get(CustomerApiController.RESOURCE_PATH + "/2")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteCustomer() throws Exception {

        when(customerService.getCustomer(managerDto.getId())).thenReturn(customerDto);

        mockMvc.perform((delete(CustomerApiController.RESOURCE_PATH + "/1")))
                .andExpect(status().isOk());
    }

    @Test
    public void getCustomerToDeleteNotFound() throws Exception {
        when(customerService.getCustomer(new Long(2))).thenReturn(null);

        mockMvc.perform(delete(CustomerApiController.RESOURCE_PATH + "/2")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateCustomer() throws Exception {

        when(customerService.getCustomer(customerDto.getId())).thenReturn(customerDto);

        customerDto.setFirstName("Jacob");

        mockMvc.perform(put(CustomerApiController.RESOURCE_PATH + "/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(new ObjectMapper().writeValueAsString(customerDto)))
                .andExpect(status().isNoContent());
    }

    @Test
    @Ignore
    public void updateCustomerBadRequest() throws Exception {
        customerDto.setFirstName("");

        mockMvc.perform(put(CustomerApiController.RESOURCE_PATH + "/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(new ObjectMapper().writeValueAsString(customerDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getCustomerToUpdateNotFound() throws Exception {
        when(customerService.getCustomer(new Long(2))).thenReturn(null);

        mockMvc.perform(put(CustomerApiController.RESOURCE_PATH + "/2")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(new ObjectMapper().writeValueAsString(customerDto)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


}