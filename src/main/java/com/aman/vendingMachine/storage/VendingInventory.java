/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aman.vendingMachine.storage;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aprashant
 */

/**
 * 
 * NOTE CONCURRENCY ISSUE: https://github.com/aprashant1/VendingMachine/issues/4
 * Assumption:
 * This should not be a concern, as this code is for vending Machine and 
 * only one user would be accessing these API at any given instance
 * 
 * 
 */
@Repository
public class VendingInventory<T> implements Inventory<T> {

    private final Map<T, Integer> inventory = new HashMap<>();

    @Override
    public int getQuantity(T item) {
        return inventory.getOrDefault(item, 0);
    }

    @Override
    public void add(T item) {
        int count = inventory.getOrDefault(item, 0);
        inventory.put(item, count + 1);
    }

    @Override
    public void withdraw(T item) {
        if (hasItem(item)) {
            int count = inventory.get(item);
            inventory.put(item, count - 1);
        }
    }

    @Override
    public boolean hasItem(T item) {
        return getQuantity(item) > 0;
    }

    @Override
    public void clear() {
        inventory.clear();
    }

    @Override
    public void put(T item, int quantity) {
        inventory.put(item, quantity);
    }
}
