/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aman.vendingMachine.controller;

import com.aman.vendingMachine.exception.ItemMaxedException;
import com.aman.vendingMachine.exception.ItemNotSupportedException;
import com.aman.vendingMachine.entity.VendingMachineResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author aprashant
 */
@RestControllerAdvice
@RequestMapping("/")
public class ExceptionControllerAdvice {

    @ExceptionHandler(ItemNotSupportedException.class)
    public ResponseEntity<VendingMachineResponse> handleAuthorizationExceptions(ItemNotSupportedException exception) {

        return new ResponseEntity<>(new VendingMachineResponse(exception.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ItemMaxedException.class)
    public ResponseEntity<VendingMachineResponse> handleAuthorizationExceptions(ItemMaxedException exception) {

        return new ResponseEntity<>(new VendingMachineResponse(exception.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
