# Fawry E-Commerce Java Project

Simple Java OOP console application demonstrating SOLID principles and design patterns.

## Design Patterns Implemented

- **Factory Pattern**: ProductFactory for creating different product types
- **Builder Pattern**: CustomerBuilder for building Customer objects
- **Strategy Pattern**: Shipping fee calculation strategies

## SOLID Principles

- **Single Responsibility**: Each class has one reason to change
- **Open/Closed**: Extensible through inheritance and composition
- **Liskov Substitution**: Subtypes are interchangeable
- **Interface Segregation**: Focused interfaces
- **Dependency Inversion**: High-level modules don't depend on low-level modules

## How to Compile

```bash
javac -d bin src/main/java/com/fawry/model/*.java src/main/java/com/fawry/factory/*.java src/main/java/com/fawry/builder/*.java src/main/java/com/fawry/service/*.java src/main/java/com/fawry/EcommerceApplication.java
```

## How to Run

```bash
java -cp bin com.fawry.EcommerceApplication
```

## Or compile and run in one command:

```bash
javac -d bin src/main/java/com/fawry/model/*.java src/main/java/com/fawry/factory/*.java src/main/java/com/fawry/builder/*.java src/main/java/com/fawry/service/*.java src/main/java/com/fawry/EcommerceApplication.java && java -cp bin com.fawry.EcommerceApplication
```

## Project Structure

```
src/
  main/java/com/fawry/
    model/      # Product, Customer, Cart, Order, CartItem, OrderItem
                # PerishableProduct, PhysicalProduct, DigitalProduct
    service/    # CheckoutService, PaymentService, ShippingFeeStrategy
                # WeightBasedShippingStrategy
    factory/    # ProductFactory
    builder/    # CustomerBuilder
    EcommerceApplication.java
bin/            # Compiled classes
```

## Sample Output

```
** Shipment notice **
2x Cheese 400g
1x Biscuits 300g
Total package weight 1.1kg
** Checkout receipt **
2x Cheese 200
1x Biscuits 150
----------------------
Subtotal 350
Shipping 30
Amount 380
```

## Features

- **Product Management**: Support for perishable, physical, and digital products
- **Shopping Cart**: Add, remove, and update items
- **Checkout Process**: Validation, payment processing, and shipping calculation
- **Customer Management**: Customer creation with builder pattern
- **Order Processing**: Complete order lifecycle management

---

Clean, minimal Java OOP project with SOLID principles and design patterns.
