package com.fawry.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PerishableProduct extends Product {
    private LocalDate expiryDate;
    private double weightKg;
    
    public PerishableProduct() {}
    
    public PerishableProduct(Long id, String name, BigDecimal price, Integer quantity, 
                           String description, LocalDate expiryDate, double weightKg) {
        super(id, name, price, quantity, description);
        this.expiryDate = expiryDate;
        this.weightKg = weightKg;
    }
    
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    
    public double getWeightKg() { return weightKg; }
    public void setWeightKg(double weightKg) { this.weightKg = weightKg; }
    
    @Override
    public boolean isPerishable() {
        return true;
    }
    
    @Override
    public boolean isShippable() {
        return true;
    }
    
    @Override
    public String getProductType() {
        return "PERISHABLE";
    }
    
    @Override
    protected void validateSpecific() {
        if (expiryDate == null) {
            throw new IllegalArgumentException("Expiry date cannot be null for perishable products");
        }
        if (weightKg <= 0) {
            throw new IllegalArgumentException("Weight must be positive for perishable products");
        }
    }
    
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }
    
    @Override
    protected double getProductWeight() {
        return weightKg;
    }
} 