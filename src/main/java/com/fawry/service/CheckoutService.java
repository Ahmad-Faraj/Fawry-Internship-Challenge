package com.fawry.service;

import com.fawry.model.*;
import java.math.BigDecimal;

public class CheckoutService {
    private PaymentService paymentService = new PaymentService();
    private ShippingFeeStrategy shippingStrategy = new WeightBasedShippingStrategy();
    
    public Order processCheckout(Customer customer, Cart cart, String shippingAddress) {
        if (customer == null) throw new IllegalArgumentException("Customer cannot be null");
        if (cart == null || cart.isEmpty()) throw new IllegalArgumentException("Cart cannot be null or empty");
        
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            if (!product.hasSufficientStock(item.getQuantity())) throw new IllegalArgumentException("Insufficient stock for " + product.getName());
            if (product.isPerishable()) {
                PerishableProduct perishable = (PerishableProduct) product;
                if (perishable.isExpired()) throw new IllegalArgumentException("Product " + product.getName() + " is expired");
            }
        }
        
        Order order = new Order();
        order.setCustomer(customer);
        order.setShippingAddress(shippingAddress);
        
        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem(cartItem.getProduct(), cartItem.getQuantity());
            order.addItem(orderItem);
        }
        
        BigDecimal shippingFee = shippingStrategy.calculateShippingFee(order);
        order.setShippingFee(shippingFee);
        
        paymentService.processPayment(customer, order);
        
        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceStock(item.getQuantity());
        }
        
        return order;
    }
} 