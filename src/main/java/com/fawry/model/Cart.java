package com.fawry.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;
    
    public Cart() {
        this.items = new ArrayList<>();
    }
    
    public List<CartItem> getItems() { return items; }
    
    public void addItem(Product product, int quantity) {
        if (product == null) throw new IllegalArgumentException();
        if (quantity <= 0) throw new IllegalArgumentException();
        if (!product.hasSufficientStock(quantity)) throw new IllegalArgumentException();
        
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        
        items.add(new CartItem(product, quantity));
    }
    
    public void removeItem(Long productId) {
        items.removeIf(item -> item.getProduct().getId().equals(productId));
    }
    
    public void updateQuantity(Long productId, int newQuantity) {
        if (newQuantity <= 0) { 
            removeItem(productId); 
            return; 
        }
        
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(productId)) {
                if (!item.getProduct().hasSufficientStock(newQuantity)) throw new IllegalArgumentException();
                item.setQuantity(newQuantity);
                return;
            }
        }
    }
    
    public void clear() {
        items.clear();
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    public int getItemCount() {
        return items.size();
    }
    
    public BigDecimal getTotalPrice() {
        return items.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    public double getTotalWeight() {
        return items.stream()
                .mapToDouble(CartItem::getTotalWeight)
                .sum();
    }
    
    public boolean hasShippableItems() {
        return items.stream().anyMatch(CartItem::isShippable);
    }
    
    public boolean hasPerishableItems() {
        return items.stream().anyMatch(CartItem::isPerishable);
    }
} 