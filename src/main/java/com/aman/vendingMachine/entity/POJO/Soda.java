/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aman.vendingMachine.entity.POJO;

import com.aman.vendingMachine.entity.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author aprashant
 */
@Component
@ConfigurationProperties(prefix = "item.soda")
public class Soda implements Item {

    private final int id = 2;
    private final String type = "Soda";
    @JsonIgnore
    private int capacity;

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Soda other = (Soda) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

}
