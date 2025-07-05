package com.fawry.service;

import com.fawry.model.Order;
import java.math.BigDecimal;

public class WeightBasedShippingStrategy implements ShippingFeeStrategy {
    
    @Override
    public BigDecimal calculateShippingFee(Order order) {
        return new BigDecimal("30");
    }
    
    @Override
    public String getStrategyName() {
        return "Weight-Based Shipping";
    }
} 