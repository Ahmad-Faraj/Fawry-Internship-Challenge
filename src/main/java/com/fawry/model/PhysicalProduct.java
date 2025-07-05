package com.fawry.model;

import java.math.BigDecimal;

public class PhysicalProduct extends Product {
    private double weightKg;
    private String dimensions;
    
    public PhysicalProduct() {}
    
    public PhysicalProduct(Long id, String name, BigDecimal price, Integer quantity, 
                          String description, double weightKg, String dimensions) {
        super(id, name, price, quantity, description);
        this.weightKg = weightKg;
        this.dimensions = dimensions;
    }
    
    public double getWeightKg() { return weightKg; }
    public void setWeightKg(double weightKg) { this.weightKg = weightKg; }
    
    public String getDimensions() { return dimensions; }
    public void setDimensions(String dimensions) { this.dimensions = dimensions; }
    
    @Override
    public boolean isPerishable() {
        return false;
    }
    
    @Override
    public boolean isShippable() {
        return true;
    }
    
    @Override
    public String getProductType() {
        return "PHYSICAL";
    }
    
    @Override
    protected void validateSpecific() {
        if (weightKg <= 0) {
            throw new IllegalArgumentException("Weight must be positive for physical products");
        }
        if (dimensions == null || dimensions.trim().isEmpty()) {
            throw new IllegalArgumentException("Dimensions cannot be null for physical products");
        }
    }
    
    @Override
    protected double getProductWeight() {
        return weightKg;
    }
} 