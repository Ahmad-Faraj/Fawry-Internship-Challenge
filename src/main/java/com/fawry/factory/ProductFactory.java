package com.fawry.factory;

import com.fawry.model.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductFactory {
    public static Product createPerishableProduct(Long id, String name, BigDecimal price, Integer quantity, String description, LocalDate expiryDate, double weightKg) {
        PerishableProduct product = new PerishableProduct(id, name, price, quantity, description, expiryDate, weightKg);
        product.validate();
        return product;
    }
    
    public static Product createPhysicalProduct(Long id, String name, BigDecimal price, Integer quantity, String description, double weightKg, String dimensions) {
        PhysicalProduct product = new PhysicalProduct(id, name, price, quantity, description, weightKg, dimensions);
        product.validate();
        return product;
    }
    
    public static Product createDigitalProduct(Long id, String name, BigDecimal price, Integer quantity, String description, String downloadUrl, String fileSize) {
        DigitalProduct product = new DigitalProduct(id, name, price, quantity, description, downloadUrl, fileSize);
        product.validate();
        return product;
    }
} 