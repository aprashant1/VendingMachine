/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aman.vendingMachine.entity;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author aprashant
 */
@Component
public class ItemResolver {

    @Autowired
    private List<Item> items;

    public static Map<String, Item> resolverByName;
    public static Map<Integer, Item> resolverById;

    @PostConstruct
    private void constructResolver() {

        resolverByName = Collections.unmodifiableMap(items.stream().collect(Collectors.toMap(item -> item.getType(), item -> item)));
        resolverById = Collections.unmodifiableMap(items.stream().collect(Collectors.toMap(item -> item.getId(), item -> item)));
    }
}
