/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aman.vendingMachine.service;

import com.aman.vendingMachine.exception.ItemNotSupportedException;
import com.aman.vendingMachine.exception.ItemMaxedException;
import com.aman.vendingMachine.entity.Item;
import com.aman.vendingMachine.entity.VendingMachineResponse;
import java.util.List;

/**
 *
 * @author aprashant
 */
public interface VendingMachineService {
    
    VendingMachineResponse addItem(Item item) throws ItemNotSupportedException,ItemMaxedException;
    VendingMachineResponse addItem(String itemType) throws ItemNotSupportedException,ItemMaxedException;
    VendingMachineResponse addItem(int itemId) throws ItemNotSupportedException,ItemMaxedException;
    VendingMachineResponse withDrawItem(Item item) throws ItemNotSupportedException;
    VendingMachineResponse withDrawItem(String itemType) throws ItemNotSupportedException;
    VendingMachineResponse withDrawItem(int itemId) throws ItemNotSupportedException;
    List<Item> getAllSupportedItems();
//    List<Item> getAllAvailableItems();
}
