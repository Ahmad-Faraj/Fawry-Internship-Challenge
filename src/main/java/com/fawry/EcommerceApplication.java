package com.fawry;

import com.fawry.model.*;
import com.fawry.service.CheckoutService;
import com.fawry.factory.ProductFactory;
import com.fawry.builder.CustomerBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;

public class EcommerceApplication {
    
    public static void main(String[] args) {
        try {
            Product cheese = ProductFactory.createPerishableProduct(1L, "Cheese", new BigDecimal("100.00"), 10, "Fresh dairy cheese", LocalDate.of(2026, 2, 15), 0.4);
            Product tv = ProductFactory.createPhysicalProduct(2L, "TV", new BigDecimal("5000.00"), 5, "55-inch Smart TV", 15.0, "120x70x10cm");
            Product scratchCard = ProductFactory.createDigitalProduct(3L, "Scratch Card", new BigDecimal("50.00"), 100, "Digital scratch card", "https://fawry.com/cards", "1MB");
            
            Customer customer = new CustomerBuilder()
                .id(1L)
                .name("John Doe")
                .email("john@example.com")
                .balance(new BigDecimal("20000.00"))
                .address("123 Main St, Cairo, Egypt")
                .build();
            
            Cart cart = new Cart();
            cart.addItem(cheese, 2);
            cart.addItem(tv, 3);
            cart.addItem(scratchCard, 1);
            
            System.out.println("** Shipment notice **");
            for (CartItem item : cart.getItems()) {
                System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + " " + (int)(item.getProduct().getWeight() * 1000) + "g");
            }
            System.out.println("Total package weight " + String.format("%.1f", cart.getTotalWeight()) + "kg");
            
            System.out.println("** Checkout receipt **");
            CheckoutService checkoutService = new CheckoutService();
            Order order = checkoutService.processCheckout(customer, cart, customer.getAddress());
            
            for (OrderItem item : order.getItems()) {
                System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + " " + item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())).intValue());
            }
            
            System.out.println("----------------------");
            System.out.println("Subtotal " + order.getSubtotal().intValue());
            System.out.println("Shipping " + order.getShippingFee().intValue());
            System.out.println("Amount " + (order.getSubtotal().intValue() + order.getShippingFee().intValue()));
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
} 