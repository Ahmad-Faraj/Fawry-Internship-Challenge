package com.fawry.service;

import com.fawry.model.Order;
import java.math.BigDecimal;

public interface ShippingFeeStrategy {
    BigDecimal calculateShippingFee(Order order);
    String getStrategyName();
} 