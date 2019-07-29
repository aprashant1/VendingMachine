/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aman.vendingMachine.service;

import com.aman.vendingMachine.entity.POJO.Candy;
import com.aman.vendingMachine.entity.Item;
import com.aman.vendingMachine.entity.ItemResolver;
import com.aman.vendingMachine.entity.VendingMachineResponse;
import com.aman.vendingMachine.exception.ItemMaxedException;
import com.aman.vendingMachine.exception.ItemNotSupportedException;
import com.aman.vendingMachine.storage.ModifiedVendingInventory;
import com.aman.vendingMachine.storage.ModifiedVendingInventoryImpl;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author aprashant
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class VendingMachineServiceImplTest {

    @InjectMocks
    private VendingMachineServiceImpl service;

    @Mock
    private List<ModifiedVendingInventory<Item>> vendingMachineInventory;
    
    //@Mock
    // private ModifiedVendingInventory<Item> mockInventory;

    @Test
    public void addItem_success_test() throws ItemNotSupportedException, ItemMaxedException {

        Candy testItem = new Candy();
        ModifiedVendingInventory<Item> mockInventory =  new ModifiedVendingInventoryImpl<>(testItem,3,1); 
        when(vendingMachineInventory.stream()).thenReturn(Stream.of(mockInventory));
        VendingMachineResponse actualResponse = service.addItem(testItem);
        assertEquals("OK", actualResponse.getStatus());
    }

    @Test(expected = ItemMaxedException.class)
    public void addItem_itemMaxedException_test() throws ItemMaxedException {

        Candy testItem = new Candy();
        ModifiedVendingInventory<Item> mockInventory =  new ModifiedVendingInventoryImpl<>(testItem,1,1); 
        when(vendingMachineInventory.stream()).thenReturn(Stream.of(mockInventory));
        service.addItem(testItem);
    }

    @Test
    public void addItemName_success_test() throws ItemNotSupportedException, ItemMaxedException {

        Item testItem = ItemResolver.resolverByName.get("Candy");
        ModifiedVendingInventory<Item> mockInventory = new ModifiedVendingInventoryImpl<>(testItem, 2, 1);
        when(vendingMachineInventory.stream()).thenReturn(Stream.of(mockInventory));
        VendingMachineResponse actualResponse = service.addItem("Candy");
        assertEquals("OK", actualResponse.getStatus());
    }

    @Test(expected = ItemNotSupportedException.class)
    public void addItemName_itemNotSupportedException_test() throws ItemNotSupportedException, ItemMaxedException {

        Candy testItem = new Candy();
        ModifiedVendingInventory<Item> mockInventory = new ModifiedVendingInventoryImpl<>(testItem, 2, 1);
        when(vendingMachineInventory.stream()).thenReturn(Stream.of(mockInventory));
        service.addItem("Test");

    }

    @Test(expected = ItemMaxedException.class)
    public void addItemName_itemMaxedException_test() throws ItemNotSupportedException, ItemMaxedException {

        Item testItem = ItemResolver.resolverByName.get("Candy");
        ModifiedVendingInventory<Item> mockInventory = new ModifiedVendingInventoryImpl<>(testItem, 1, 1);
        when(vendingMachineInventory.stream()).thenReturn(Stream.of(mockInventory));
        service.addItem("Candy");

    }

    @Test
    public void withdrawItem_success_test() throws ItemNotSupportedException, ItemMaxedException {

        Candy testItem = new Candy();
        ModifiedVendingInventory<Item> mockInventory = new ModifiedVendingInventoryImpl<>(testItem, 2, 1);
        when(vendingMachineInventory.stream()).thenReturn(Stream.of(mockInventory));
        VendingMachineResponse response = service.withDrawItem(testItem);
        assertEquals("OK", response.getStatus());
    }

    @Test
    public void withdrawItem_failure_test() throws ItemNotSupportedException, ItemMaxedException {

        Candy testItem = new Candy();
        ModifiedVendingInventory<Item> mockInventory = new ModifiedVendingInventoryImpl<>(testItem, 2, 0);
        when(vendingMachineInventory.stream()).thenReturn(Stream.of(mockInventory));
        VendingMachineResponse response = service.withDrawItem(testItem);
        assertEquals("N/A", response.getStatus());
    }

    @Test
    public void withDrawItemName_success_test() throws ItemNotSupportedException {

        Item testItem = ItemResolver.resolverByName.get("Candy");
        ModifiedVendingInventory<Item> mockInventory = new ModifiedVendingInventoryImpl<>(testItem, 2, 1);
        when(vendingMachineInventory.stream()).thenReturn(Stream.of(mockInventory));
        VendingMachineResponse response = service.withDrawItem("Candy");
        assertEquals("OK", response.getStatus());
    }

    @Test
    public void withDrawItemName_failure_test() throws ItemNotSupportedException {

        Item testItem = ItemResolver.resolverByName.get("Candy");
        ModifiedVendingInventory<Item> mockInventory = new ModifiedVendingInventoryImpl<>(testItem, 2, 0);
        when(vendingMachineInventory.stream()).thenReturn(Stream.of(mockInventory));
        VendingMachineResponse response = service.withDrawItem("Candy");
        assertEquals("N/A", response.getStatus());
    }

    @Test
    public void getAllSupportedItems_test() {
        List<Item> expected = ItemResolver.resolverByName.entrySet()
                .stream().map(entry -> entry.getValue())
                .collect(Collectors.toList());
        List<Item> supportedItems = service.getAllSupportedItems();

        assertEquals(expected, supportedItems);
    }
}
