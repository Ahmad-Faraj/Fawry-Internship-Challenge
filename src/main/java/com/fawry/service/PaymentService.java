package com.fawry.service;

import com.fawry.model.Customer;
import com.fawry.model.Order;
import java.math.BigDecimal;

public class PaymentService {
    
    public void processPayment(Customer customer, Order order) {
        BigDecimal totalAmount = order.getTotal();
        
        if (!customer.hasSufficientBalance(totalAmount)) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        
        customer.deductBalance(totalAmount);
    }
} 