package com.fawry.model;

import java.math.BigDecimal;

public class CartItem {
    private Product product;
    private int quantity;
    
    public CartItem() {}
    
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public BigDecimal getTotalPrice() {
        return product.calculateTotalPrice(quantity);
    }
    
    public double getTotalWeight() {
        return product.getWeight() * quantity;
    }
    
    public boolean isShippable() {
        return product.isShippable();
    }
    
    public boolean isPerishable() {
        return product.isPerishable();
    }
} 