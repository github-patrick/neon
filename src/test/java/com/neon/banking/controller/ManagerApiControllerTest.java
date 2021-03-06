package com.neon.banking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neon.banking.dto.ManagerDto;
import com.neon.banking.exceptions.ModelConstraintErrorException;
import com.neon.banking.mapper.ManagerMapper;
import com.neon.banking.model.Manager;
import com.neon.banking.service.ManagerService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ManagerApiControllerTest {

    @Mock
    private ManagerService managerService;

    private MockMvc mockMvc;
    private ManagerApiController managerApiController;
    private ManagerDto managerDto;

    @Before
    public void setUp() {

        managerApiController = new ManagerApiController(managerService);
        mockMvc = MockMvcBuilders.standaloneSetup(managerApiController).build();
        managerDto = ManagerDto.builder().id(new Long(1)).firstName("Mark").lastName("Matthew").username("markup")
                .password("password").customers(null).build();

    }

    @Test
    public void createManager() throws Exception {

        mockMvc.perform(post(ManagerApiController.RESOURCE_PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(new ObjectMapper().writeValueAsString(managerDto)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Ignore("stubbed out until a reasonable way to mock out collaborators can be thought of")
    public void CreateManagerBadRequest() throws Exception {

        managerDto.setPassword("he");

        mockMvc.perform(post(ManagerApiController.RESOURCE_PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(new ObjectMapper().writeValueAsString(managerDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getManagers() throws Exception {
        mockMvc.perform(get(ManagerApiController.RESOURCE_PATH)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void getManager() throws Exception {
        when(managerService.getManager(managerDto.getId())).thenReturn(managerDto);

        mockMvc.perform(get(ManagerApiController.RESOURCE_PATH + "/1")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void getManagerNotFound() throws Exception {
        when(managerService.getManager(new Long(2))).thenReturn(null);

        mockMvc.perform(get(ManagerApiController.RESOURCE_PATH + "/2")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteManager() throws Exception {

        when(managerService.getManager(managerDto.getId())).thenReturn(managerDto);

        mockMvc.perform((delete(ManagerApiController.RESOURCE_PATH + "/1")))
                .andExpect(status().isOk());
    }

    @Test
    public void getManagerToDeleteNotFound() throws Exception {
        when(managerService.getManager(new Long(2))).thenReturn(null);

        mockMvc.perform(delete(ManagerApiController.RESOURCE_PATH + "/2")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateManager() throws Exception {

        when(managerService.getManager(managerDto.getId())).thenReturn(managerDto);

        managerDto.setFirstName("Jacob");

        mockMvc.perform(put(ManagerApiController.RESOURCE_PATH + "/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(new ObjectMapper().writeValueAsString(managerDto)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void updateManagerBadRequest() throws Exception {
        managerDto.setFirstName("");

        mockMvc.perform(put(ManagerApiController.RESOURCE_PATH + "/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(new ObjectMapper().writeValueAsString(managerDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getManagerToUpdateNotFound() throws Exception {
        when(managerService.getManager(new Long(2))).thenReturn(null);

        mockMvc.perform(put(ManagerApiController.RESOURCE_PATH + "/2")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(new ObjectMapper().writeValueAsString(managerDto)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


}