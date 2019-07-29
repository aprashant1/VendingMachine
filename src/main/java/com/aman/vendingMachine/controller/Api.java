/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aman.vendingMachine.controller;

import com.aman.vendingMachine.entity.VendingMachineResponse;
import com.aman.vendingMachine.exception.ItemMaxedException;
import com.aman.vendingMachine.exception.ItemNotSupportedException;
import com.aman.vendingMachine.entity.Item;
import com.aman.vendingMachine.entity.VendingMachineRequest;
import com.aman.vendingMachine.service.VendingMachineService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author aprashant
 */
@RestController
@RequestMapping("/api")
public class Api {
    
    @Autowired
    private VendingMachineService service;
    
    @PostMapping("/deposit")
    public ResponseEntity<VendingMachineResponse> addItem(@RequestBody VendingMachineRequest request) throws ItemNotSupportedException, ItemMaxedException {
      return ResponseEntity.ok().body(service.addItem(request.getType()));
    }
    
    
    @PostMapping("/withdraw")
    public ResponseEntity<VendingMachineResponse> withdrawItem(@RequestBody VendingMachineRequest request) throws ItemNotSupportedException {
      return ResponseEntity.ok().body(service.withDrawItem(request.getType()));
    }
    
    @GetMapping("/getlist")
    public ResponseEntity<List<Item>> supportedItems() {
      return ResponseEntity.ok().body(service.getAllSupportedItems());
    }
}
