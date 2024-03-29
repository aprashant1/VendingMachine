/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aman.vendingMachine.service;

import com.aman.vendingMachine.service.VendingMachineServiceImpl;
import com.aman.vendingMachine.entity.POJO.Candy;
import com.aman.vendingMachine.entity.Item;
import com.aman.vendingMachine.entity.ItemResolver;
import com.aman.vendingMachine.entity.VendingMachineResponse;
import com.aman.vendingMachine.exception.ItemMaxedException;
import com.aman.vendingMachine.exception.ItemNotSupportedException;
import com.aman.vendingMachine.storage.VendingInventory;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
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
    private VendingInventory<Item> vendingInventory;

    @Test
    public void addItem_success_test() throws ItemNotSupportedException, ItemMaxedException {

        Candy testItem = new Candy();
        testItem.setCapacity(3);
        when(vendingInventory.getQuantity(ArgumentMatchers.any(Item.class))).thenReturn(2);
        service.addItem(testItem);

        // verify add method is called
        verify(vendingInventory).add(testItem);

        // verify arg passed to add method
        ArgumentCaptor<Item> argCaptor = ArgumentCaptor.forClass(Item.class);
        verify(vendingInventory).add(argCaptor.capture());
        assertEquals(testItem, argCaptor.getValue());

    }

    @Test(expected = ItemMaxedException.class)
    public void addItem_itemMaxedException_test() throws ItemMaxedException {

        Candy testItem = new Candy();
        testItem.setCapacity(1);
        when(vendingInventory.getQuantity(ArgumentMatchers.any(Item.class))).thenReturn(1);
        service.addItem(testItem);

        // verify add method is never called
        verify(vendingInventory, never()).add(testItem);

    }

    @Test
    public void addItemName_success_test() throws ItemNotSupportedException, ItemMaxedException {

        Item testItem = ItemResolver.resolverByName.get("Candy");
        when(vendingInventory.getQuantity(ArgumentMatchers.any(Item.class))).thenReturn(0);
        service.addItem("Candy");

        // verify add method is called
        verify(vendingInventory).add(testItem);

        // verify arg passed to add method
        ArgumentCaptor<Item> argCaptor = ArgumentCaptor.forClass(Item.class);
        verify(vendingInventory).add(argCaptor.capture());
        assertEquals(testItem, argCaptor.getValue());

    }

    @Test(expected = ItemNotSupportedException.class)
    public void addItemName_itemNotSupportedException_test() throws ItemNotSupportedException, ItemMaxedException {

        when(vendingInventory.getQuantity(ArgumentMatchers.any(Item.class))).thenReturn(1);
        service.addItem("Test");

    }

    @Test(expected = ItemMaxedException.class)
    public void addItemName_itemMaxedException_test() throws ItemNotSupportedException, ItemMaxedException {

        Item testItem = ItemResolver.resolverByName.get("Candy");
        when(vendingInventory.getQuantity(ArgumentMatchers.any(Item.class))).thenReturn(3);
        service.addItem("Candy");

    }

    @Test
    public void withdrawItem_success_test() throws ItemNotSupportedException, ItemMaxedException {

        Candy testItem = new Candy();
        testItem.setCapacity(3);
        when(vendingInventory.hasItem(ArgumentMatchers.any(Item.class))).thenReturn(true);
        VendingMachineResponse response = service.withDrawItem(testItem);

        // verify add method is called
        verify(vendingInventory).withdraw(testItem);

        // verify arg passed to add method
        ArgumentCaptor<Item> argCaptor = ArgumentCaptor.forClass(Item.class);
        verify(vendingInventory).withdraw(argCaptor.capture());
        assertEquals(testItem, argCaptor.getValue());

        assertEquals("OK", response.getStatus());
    }

    @Test
    public void withdrawItem_failure_test() throws ItemNotSupportedException, ItemMaxedException {

        Candy testItem = new Candy();
        testItem.setCapacity(3);
        when(vendingInventory.hasItem(ArgumentMatchers.any(Item.class))).thenReturn(false);
        VendingMachineResponse response = service.withDrawItem(testItem);

        // verify add method is called
        verify(vendingInventory, never()).withdraw(testItem);

        assertEquals("N/A", response.getStatus());
    }

    @Test
    public void withDrawItemName_success_test() throws ItemNotSupportedException {

        Item testItem = ItemResolver.resolverByName.get("Candy");
        when(vendingInventory.hasItem(ArgumentMatchers.any(Item.class))).thenReturn(true);
        VendingMachineResponse response = service.withDrawItem("Candy");

        // verify add method is called
        verify(vendingInventory).withdraw(testItem);
        // verify arg passed to add method
        ArgumentCaptor<Item> argCaptor = ArgumentCaptor.forClass(Item.class);
        verify(vendingInventory).withdraw(argCaptor.capture());
        assertEquals(testItem, argCaptor.getValue());

        assertEquals("OK", response.getStatus());
    }

    @Test
    public void withDrawItemName_failure_test() throws ItemNotSupportedException {

        Item testItem = ItemResolver.resolverByName.get("Candy");
        when(vendingInventory.hasItem(ArgumentMatchers.any(Item.class))).thenReturn(false);
        VendingMachineResponse response = service.withDrawItem("Candy");

        // verify add method is called
        verify(vendingInventory, never()).withdraw(testItem);
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
