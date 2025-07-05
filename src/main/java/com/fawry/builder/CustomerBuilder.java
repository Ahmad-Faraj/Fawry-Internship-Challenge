package com.fawry.builder;

import com.fawry.model.Customer;
import java.math.BigDecimal;

public class CustomerBuilder {
    private Long id;
    private String name;
    private String email;
    private BigDecimal balance;
    private String address;
    
    public CustomerBuilder id(Long id) {
        this.id = id;
        return this;
    }
    
    public CustomerBuilder name(String name) {
        this.name = name;
        return this;
    }
    
    public CustomerBuilder email(String email) {
        this.email = email;
        return this;
    }
    
    public CustomerBuilder balance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }
    
    public CustomerBuilder address(String address) {
        this.address = address;
        return this;
    }
    
    public Customer build() {
        if (id == null || name == null || email == null || balance == null || address == null) {
            throw new IllegalArgumentException("All fields are required");
        }
        return new Customer(id, name, email, balance, address);
    }
} 