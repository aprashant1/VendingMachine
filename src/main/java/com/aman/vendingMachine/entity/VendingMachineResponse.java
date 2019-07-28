/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aman.vendingMachine.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 *
 * @author aprashant
 */
@JsonInclude(Include.NON_NULL)
public class VendingMachineResponse {
    private Integer id;
    private String status;

    public VendingMachineResponse() {
    }

    
    public VendingMachineResponse(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public VendingMachineResponse(String status) {
        this.status = status;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
