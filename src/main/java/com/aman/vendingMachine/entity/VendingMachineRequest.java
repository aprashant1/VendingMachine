/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aman.vendingMachine.entity;

/**
 *
 * @author aprashant
 */
public class VendingMachineRequest {

    public VendingMachineRequest(String type) {
        this.type = type;
    }

    public VendingMachineRequest() {
    }
    
    
    
    public String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
