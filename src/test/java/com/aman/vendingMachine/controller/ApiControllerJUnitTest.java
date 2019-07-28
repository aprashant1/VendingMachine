/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aman.vendingMachine.controller;

import com.aman.vendingMachine.exception.ItemMaxedException;
import com.aman.vendingMachine.exception.ItemNotSupportedException;
import com.aman.vendingMachine.entity.POJO.Candy;
import com.aman.vendingMachine.entity.Item;
import com.aman.vendingMachine.entity.VendingMachineRequest;
import com.aman.vendingMachine.entity.VendingMachineResponse;
import com.aman.vendingMachine.entity.POJO.Toy;
import com.aman.vendingMachine.service.VendingMachineService;
import com.aman.vendingMachine.utility.ApiTestUtility;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author aprashant
 */
@RunWith(SpringRunner.class)
@WebMvcTest(Api.class)
public class ApiControllerJUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendingMachineService service;

    @Test
    public void addItem_success_test() throws Exception {
        when(service.addItem(ArgumentMatchers.anyString())).thenReturn(new VendingMachineResponse(1, "test"));
        VendingMachineRequest requestBody = new VendingMachineRequest();
        requestBody.setType("test");
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/deposit")
                .content(ApiTestUtility.asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"status\":\"test\"}"))
                .andReturn();

    }
    
    @Test
    public void addItem_ItemNotSupported_test() throws Exception {
        when(service.addItem(ArgumentMatchers.anyString())).thenThrow(new ItemNotSupportedException("Item not supported"));
        VendingMachineRequest requestBody = new VendingMachineRequest();
        requestBody.setType("test");
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/deposit")
                .content(ApiTestUtility.asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().json("{\"status\":\"Item not supported\"}"))
                .andReturn();

    }
    
     @Test
    public void addItem_ItemMaxedException_test() throws Exception {
        when(service.addItem(ArgumentMatchers.anyString())).thenThrow(new ItemMaxedException("Test item can't be added. Queue is at max"));
        VendingMachineRequest requestBody = new VendingMachineRequest();
        requestBody.setType("test");
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/deposit")
                .content(ApiTestUtility.asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().json("{\"status\":\"Test item can't be added. Queue is at max\"}"))
                .andReturn();

    }
    
    @Test
    public void withdrawItem_success_test() throws Exception {
        //when(service.withDrawItem(ArgumentMatchers.anyString())).thenReturn(new VendingMachineResponse(1, "tested OK !"));
        when(service.withDrawItem("test")).thenReturn(new VendingMachineResponse(1, "tested OK !"));
        VendingMachineRequest requestBody = new VendingMachineRequest();
        requestBody.setType("test");
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/withdraw")
                .content(ApiTestUtility.asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        
        MvcResult result;
        result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":\"tested OK !\"}"))
                .andReturn();

    }
    
     @Test
    public void withdrawItem_ItemNotSupported_test() throws Exception {
        when(service.withDrawItem(ArgumentMatchers.anyString())).thenThrow(new ItemNotSupportedException("Item not supported"));
        VendingMachineRequest requestBody = new VendingMachineRequest();
        requestBody.setType("test");
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/withdraw")
                .content(ApiTestUtility.asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().json("{\"status\":\"Item not supported\"}"))
                .andReturn();

    }
    
     @Test
    public void SupportedItems_success_test() throws Exception {
        List<Item> supportedItems = Arrays.asList(new Candy(), new Toy());
        when(service.getAllSupportedItems()).thenReturn(supportedItems);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/getlist")
                .accept(MediaType.APPLICATION_JSON);

        
        MvcResult result;
        result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"type\":\"Candy\"},{\"id\":3,\"type\":\"Toy\"}]"))
                .andReturn();

    }
}
