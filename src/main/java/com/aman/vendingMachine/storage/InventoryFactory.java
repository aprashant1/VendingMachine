/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aman.vendingMachine.storage;

import com.aman.vendingMachine.entity.Item;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aprashant
 */
@Repository
public class InventoryFactory {

    @Autowired
    private List<Item> items;

    @Value("#{${capacity}}")
    private Map<String, Integer> capacity;

    @Bean("vendingMachineInventory")
    public List<ModifiedVendingInventory<Item>> getInventory() {
        List<ModifiedVendingInventory<Item>> inventory = items.stream().map(element -> new ModifiedVendingInventoryImpl<>(element, capacity.get(element.getType()))).
                collect(Collectors.toList());

        return inventory;
    }

}
