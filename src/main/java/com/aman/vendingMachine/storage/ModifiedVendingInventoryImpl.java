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
/**
 * 
 * NOTE CONCURRENCY ISSUE: https://github.com/aprashant1/VendingMachine/issues/4
 * Assumption:
 * This should not be a concern, as this code is for vending Machine and 
 * only one user would be accessing these API at any given instance
 * 
 * 
 */
public class ModifiedVendingInventoryImpl<T extends Item> implements ModifiedVendingInventory<T> {
    
    private T item;
    private int capacity;
    private int currentCount;

    public ModifiedVendingInventoryImpl(T item, int capacity) {
        this(item, capacity, 0);
    }

    public ModifiedVendingInventoryImpl(T item, int capacity, int currentCount) {
        this.item = item;
        this.capacity = capacity;
        this.currentCount = currentCount;
    }
    
    
    

    @Override
    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }
    
    @Override
    public void add(T item) throws ItemMaxedException {
       if(this.currentCount < this.capacity) {
           this.currentCount++;
       } else {
            throw new ItemMaxedException(this.getItem().getType() + " can't be added. Queue is at max, items: " + this.getCapacity());
        }
    }

    @Override
    public void clear() {
        this.currentCount = 0;
    }

    @Override
    public int getQuantity(T item) {
        return this.currentCount;
    }

    @Override
    public boolean hasItem(T item) {
        return this.currentCount > 0;
    }

    
    // add and put are same.
    @Override
    public void put(T item, int quantity) throws ItemMaxedException {
        if(this.currentCount < this.capacity) {
           this.currentCount++;
       } else {
            throw new ItemMaxedException(this.getItem().getType() + " can't be added. Queue is at max, items: " + this.getCapacity());
        }
    }

    @Override
    public boolean withdraw(T item) {
        if (hasItem(item)) {
            this.currentCount = this.currentCount - 1;
            return true;
        } else {
            return false;
        }
    }
    
    
    
}
