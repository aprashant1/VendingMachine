/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aman.vendingMachine.service;

import com.aman.vendingMachine.exception.ItemNotSupportedException;
import com.aman.vendingMachine.exception.ItemMaxedException;
import com.aman.vendingMachine.entity.Item;
import com.aman.vendingMachine.entity.ItemResolver;
import com.aman.vendingMachine.entity.VendingMachineResponse;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aman.vendingMachine.storage.Inventory;

/**
 *
 * @author aprashant
 */
@Service
public class VendingMachineServiceImpl implements VendingMachineService {

    @Autowired
    private Inventory<Item> vendingInventory;

    @Override
    public VendingMachineResponse addItem(Item item) throws ItemMaxedException {
        if (vendingInventory.getQuantity(item) < item.getCapacity()) {
            vendingInventory.add(item);
        } else {
            throw new ItemMaxedException(item.getType() + " can't be added. Queue is at max, items: " + item.getCapacity());
        }
        return new VendingMachineResponse(item.getId(), "OK");
    }

    @Override
    public VendingMachineResponse addItem(String item) throws ItemNotSupportedException, ItemMaxedException {
        Item resolvedItem = ItemResolver.resolverByName.get(item);
        if (Objects.isNull(resolvedItem)) {
            throw new ItemNotSupportedException("Item not supported");
        }
        return addItem(resolvedItem);
    }

    @Override
    public VendingMachineResponse addItem(int itemId) throws ItemNotSupportedException, ItemMaxedException {
        Item resolvedItem = ItemResolver.resolverById.get(itemId);
        if (Objects.isNull(resolvedItem)) {
            throw new ItemNotSupportedException("Item not supported");
        }
        return addItem(resolvedItem);
    }

    @Override
    public VendingMachineResponse withDrawItem(Item item) {
        String responseMessage;
        if (vendingInventory.hasItem(item)) {
            vendingInventory.withdraw(item);
            responseMessage = "OK";
        } else {
            responseMessage = "N/A";
        }
        return new VendingMachineResponse(null,responseMessage);
    }

    @Override
    public VendingMachineResponse withDrawItem(String item) throws ItemNotSupportedException {
        Item resolvedItem = ItemResolver.resolverByName.get(item);
        if (Objects.isNull(resolvedItem)) {
            throw new ItemNotSupportedException("Item not supported");
        }
        return withDrawItem(resolvedItem);
    }

    @Override
    public VendingMachineResponse withDrawItem(int itemId) throws ItemNotSupportedException {
        Item resolvedItem = ItemResolver.resolverById.get(itemId);
        if (Objects.isNull(resolvedItem)) {
            throw new ItemNotSupportedException("Item not supported");
        }
        return withDrawItem(resolvedItem);
    }

    @Override
    public List<Item> getAllSupportedItems() {
        List<Item> supportedItems = ItemResolver.resolverByName.entrySet()
                .stream().map(Entry::getValue)
                .collect(Collectors.toList());
        return supportedItems;
    }

    @Override
    public List<Item> getAllAvailableItems() {
        List<Item> availableItems = ItemResolver.resolverByName.entrySet()
                .stream().map(Entry::getValue)
                .filter(item -> vendingInventory.hasItem(item))
                .collect(Collectors.toList());
        return availableItems;
    }

}
