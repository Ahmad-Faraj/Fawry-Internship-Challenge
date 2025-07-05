package com.fawry.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private Customer customer;
    private List<OrderItem> items;
    private BigDecimal subtotal;
    private BigDecimal shippingFee;
    private BigDecimal total;
    private String status;
    private LocalDateTime orderDate;
    private String shippingAddress;
    
    public Order() {
        this.items = new ArrayList<>();
        this.orderDate = LocalDateTime.now();
        this.status = "PENDING";
    }
    
    public Order(Long id, Customer customer, String shippingAddress) {
        this();
        this.id = id;
        this.customer = customer;
        this.shippingAddress = shippingAddress;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
    
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    
    public BigDecimal getShippingFee() { return shippingFee; }
    public void setShippingFee(BigDecimal shippingFee) { this.shippingFee = shippingFee; }
    
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    
    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
    
    public void addItem(OrderItem item) {
        items.add(item);
        recalculateTotals();
    }
    
    public void removeItem(Long productId) {
        items.removeIf(item -> item.getProduct().getId().equals(productId));
        recalculateTotals();
    }
    
    private void recalculateTotals() {
        this.subtotal = items.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        this.total = subtotal.add(shippingFee != null ? shippingFee : BigDecimal.ZERO);
    }
    
    public boolean isShippable() {
        return items.stream().anyMatch(item -> item.getProduct().isShippable());
    }
    
    public boolean hasPerishableItems() {
        return items.stream().anyMatch(item -> item.getProduct().isPerishable());
    }
    
    public double getTotalWeight() {
        return items.stream()
                .mapToDouble(item -> item.getProduct().getWeight() * item.getQuantity())
                .sum();
    }
} 