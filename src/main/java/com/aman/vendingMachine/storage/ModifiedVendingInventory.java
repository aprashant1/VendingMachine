/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aman.vendingMachine.storage;

import com.aman.vendingMachine.entity.Item;
import com.aman.vendingMachine.exception.ItemMaxedException;

/**
 *
 * @author aprashant
 * @param <T>
 */
public interface ModifiedVendingInventory<T extends Item> {

    void add(T item) throws ItemMaxedException;

    void clear();

    int getCapacity();

    int getCurrentCount();

    T getItem();

    int getQuantity(T item);

    boolean hasItem(T item);

    // add and put are same.
    void put(T item, int quantity) throws ItemMaxedException;
    
    boolean withdraw(T item);
    
}
