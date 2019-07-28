/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aman.vendingMachine.storage;

/**
 *
 * @author aprashant
 */
public interface Inventory<T> {

    void add(T item);

    void clear();

    int getQuantity(T item);

    boolean hasItem(T item);

    void put(T item, int quantity);

    void withdraw(T item);
    
}
