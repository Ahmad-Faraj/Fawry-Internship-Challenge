package com.fawry.model;

import java.math.BigDecimal;

public abstract class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private String description;
    
    public Product() {}
    
    public Product(Long id, String name, BigDecimal price, Integer quantity, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public abstract boolean isPerishable();
    public abstract boolean isShippable();
    public abstract String getProductType();
    
    public final void validate() {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException();
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException();
        if (quantity == null || quantity < 0) throw new IllegalArgumentException();
        validateSpecific();
    }
    
    protected abstract void validateSpecific();
    
    public boolean isInStock() {
        return quantity > 0;
    }
    
    public boolean hasSufficientStock(int requestedQuantity) {
        return quantity >= requestedQuantity;
    }
    
    public void reduceStock(int requestedQuantity) {
        if (requestedQuantity <= 0) throw new IllegalArgumentException();
        if (quantity < requestedQuantity) throw new IllegalArgumentException();
        quantity -= requestedQuantity;
    }
    
    public BigDecimal calculateTotalPrice(int quantity) {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
    
    public double getWeight() {
        return isShippable() ? getProductWeight() : 0.0;
    }
    
    protected double getProductWeight() {
        return 0.0;
    }
} 