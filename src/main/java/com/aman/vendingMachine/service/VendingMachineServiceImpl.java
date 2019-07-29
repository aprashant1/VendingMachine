///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.aman.vendingMachine.service;
//
//import com.aman.vendingMachine.exception.ItemNotSupportedException;
//import com.aman.vendingMachine.exception.ItemMaxedException;
//import com.aman.vendingMachine.entity.Item;
//import com.aman.vendingMachine.entity.ItemResolver;
//import com.aman.vendingMachine.entity.VendingMachineResponse;
//import com.aman.vendingMachine.storage.ModifiedVendingInventory;
//import java.util.List;
//import java.util.Map.Entry;
//import java.util.Objects;
//import java.util.stream.Collectors;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// *
// * @author aprashant
// */
//@Service
//public class VendingMachineServiceImpl implements VendingMachineService {
//
//    @Autowired
//    private List<ModifiedVendingInventory<Item>> vendingMachineInventory;
//
//    @Override
//    public VendingMachineResponse addItem(Item item) throws ItemMaxedException {
//       ModifiedVendingInventory<Item> itemInventory =  getInventoryRac(vendingMachineInventory, item);       
//       itemInventory.add(item);
//        return new VendingMachineResponse(item.getId(), "OK");
//    }
//
//    @Override
//    public VendingMachineResponse addItem(String item) throws ItemNotSupportedException, ItemMaxedException {
//        Item resolvedItem = ItemResolver.resolverByName.get(item);
//        if (Objects.isNull(resolvedItem)) {
//            throw new ItemNotSupportedException("Item not supported");
//        }
//        return addItem(resolvedItem);
//    }
//
//    @Override
//    public VendingMachineResponse addItem(int itemId) throws ItemNotSupportedException, ItemMaxedException {
//        Item resolvedItem = ItemResolver.resolverById.get(itemId);
//        if (Objects.isNull(resolvedItem)) {
//            throw new ItemNotSupportedException("Item not supported");
//        }
//        return addItem(resolvedItem);
//    }
//
//    @Override
//    public VendingMachineResponse withDrawItem(Item item) {
//        String responseMessage;
//        ModifiedVendingInventory<Item> itemInventory =  getInventoryRac(vendingMachineInventory, item);       
//        if (itemInventory.withdraw(item)) {
//                responseMessage = "OK";
//        } else {
//            responseMessage = "N/A";
//        }
//        return new VendingMachineResponse(null,responseMessage);
//    }
//
//    @Override
//    public VendingMachineResponse withDrawItem(String item) throws ItemNotSupportedException {
//        Item resolvedItem = ItemResolver.resolverByName.get(item);
//        if (Objects.isNull(resolvedItem)) {
//            throw new ItemNotSupportedException("Item not supported");
//        }
//        return withDrawItem(resolvedItem);
//    }
//
//    @Override
//    public VendingMachineResponse withDrawItem(int itemId) throws ItemNotSupportedException {
//        Item resolvedItem = ItemResolver.resolverById.get(itemId);
//        if (Objects.isNull(resolvedItem)) {
//            throw new ItemNotSupportedException("Item not supported");
//        }
//        return withDrawItem(resolvedItem);
//    }
//
//    @Override
//    public List<Item> getAllSupportedItems() {
//        List<Item> supportedItems = ItemResolver.resolverByName.entrySet()
//                .stream().map(Entry::getValue)
//                .collect(Collectors.toList());
//        return supportedItems;
//    }
//
//    private ModifiedVendingInventory<Item> getInventoryRac(List<ModifiedVendingInventory<Item>> vendingMachineInventory, Item item) {
//        return vendingMachineInventory.stream().filter(element -> element.equals(item)).findFirst().get();
//    }
//}
