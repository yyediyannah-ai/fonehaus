# FONE HAUS ONLINE ELECTRONICS SHOPPING APPLICATION
## Major Project Report & Technical Documentation

**Course:** IS223 Object-Oriented Programming Major Project  
**Institution:** Papua New Guinea University of Technology (PNGUoT)  
**School:** School of Business Studies  
**Programme:** Bachelor of Business in Information Technology  
**Academic Year:** 2026  

---

### Project Team Members
- **William Bineke**
- **Lesley Yapa**
- **Matthias Virano**
- **Yedi Yannah Yuwom**

---

## 1. Executive Summary / Abstract

This document presents the design, architectural analysis, implementation, and testing of the **Fone Haus Online Electronics Shopping Mobile Application**, developed using **Java** and **Android Studio**. Fone Haus is an established retail brand in Papua New Guinea specializing in mobile phones, laptops, tablets, audio accessories, smart TVs, and smart watches.

The primary objective of this project is to address traditional in-store shopping bottlenecks by providing a modern, native Android mobile commerce application. Built entirely around Object-Oriented Programming (OOP) principles, the application demonstrates encapsulation, deep inheritance chains (`Product` → `Phone` → `IPhone`, `Tablet`, `Laptop`), method overriding (`@Override displayProduct()`, `getCategoryDetails()`), polymorphism via dynamic dispatch, dynamic cart management (`Cart`, `CartManager`), a complete multi-step checkout workflow (`CheckoutActivity` and `OrderConfirmationActivity`), and domain models for `Customer` and `Order`.

Comprehensive automated unit testing was executed using JUnit 4, achieving a 100% pass rate across all core OOP methods, cart calculations, and checkout data flows.

---

## 2. Problem Identification & Business Scenario

### 2.1 Business Context
Fone Haus operates physical retail outlets across urban centers in Papua New Guinea. Customers currently visit physical stores to inquire about mobile device prices, stock availability, specifications, and payment terms.

### 2.2 Core Problems Identified
1. **Long In-Store Queues:** Customers experience extended wait times during peak hours to check product pricing or purchase accessories.
2. **Limited Product Browsing:** In-store displays are constrained by physical space, preventing customers from viewing the full range of smartphones, laptops, audio systems, and smart watches.
3. **Lack of Remote Order Placement:** Customers living outside major urban centers cannot easily select items, calculate order costs, or place delivery orders remotely.

### 2.3 Mobile Solution Strategy
The Fone Haus Mobile Shopping Application solves these problems by providing:
- Real-time catalog browsing organized by category.
- Comprehensive product specifications, images, and pricing in PNG Kina (K).
- An interactive Shopping Cart that updates total order costs dynamically.
- A functional Checkout system capturing customer contact details, delivery addresses, and payment method choices.

---

## 3. Project Objectives

1. **System Requirements & Analysis:** Formulate functional, non-functional, user, and hardware/software specifications for mobile e-commerce.
2. **System Design & Modeling:** Construct UML diagrams (Use Case, Class, Activity, and Application Navigation diagrams).
3. **Java OOP Development:** Implement OOP concepts including Encapsulation, Constructors, Getters/Setters, Inheritance, Method Overriding, and Polymorphism.
4. **Android UI & Integration:** Construct clean, mobile-responsive XML layouts adhering to a unified dark charcoal and yellow theme (`#FFC107`).
5. **Shopping & Checkout Workflow:** Build an interactive shopping cart and multi-step checkout process with input validation and order summaries.
6. **Automated Unit Testing:** Verify OOP logic, cart calculations, and order creation with JUnit test suites.

---

## 4. Requirements Specifications

### 4.1 Functional Requirements
- **FR1 (Home Navigation):** The application shall display a main dashboard with quick category chips, product catalog triggers, and a live cart item counter.
- **FR2 (Category Filtering):** Users shall be able to filter products by category (Phones, iPhones, Tablets, Laptops, Audio, Smart TVs, Smart Watches, or All Products).
- **FR3 (Product Details):** Selecting a product shall present its full image, name, category, price (in Kina), description, and an "Add to Cart" button.
- **FR4 (Shopping Cart Management):** The cart shall allow adding products, displaying items, calculating total cost dynamically, and clearing items.
- **FR5 (Checkout & Validation):** The checkout screen shall capture Full Name, Phone Number, Email, Delivery Address, and Payment Method (Cash on Delivery, Mobile Money, Card) with mandatory field validation.
- **FR6 (Order Confirmation):** Upon placing an order, the system shall generate a unique Order ID, clear the active cart, and present a complete Order Confirmation summary.

### 4.2 Non-Functional Requirements
- **NFR1 (Performance):** Screen transitions shall execute smoothly within 300ms.
- **NFR2 (Usability):** Touch targets shall maintain a minimum height of 48dp to comply with Android accessibility guidelines.
- **NFR3 (Responsiveness):** UI layouts shall utilize ConstraintLayout and dimension resources (`dimens.xml`) for tablet and phone adaptiveness.

---

## 5. System Design & UML Diagrams

### 5.1 Use Case Diagram (Textual Representation)

```
                       +----------------------------------+
                       |   Fone Haus Mobile Application   |
                       +----------------------------------+
                                        |
       +--------------------------------+--------------------------------+
       |               |                |               |                |
[ Browse Catalog ] [ View Category ] [ View Details ] [ Add to Cart ] [ View Cart ]
       |               |                |               |                |
       +--------------------------------+--------------------------------+
                                        |
                                [ Perform Checkout ]
                                        |
                            [ Receive Order Summary ]
```

### 5.2 Class Diagram Architecture

```
                       +-------------------+
                       |      Product      |
                       +-------------------+
                       | - productName     |
                       | - price           |
                       | - description     |
                       | - category        |
                       | - imageResId      |
                       +-------------------+
                       | + displayProduct()|
                       | + getCategoryDetails() |
                       +-------------------+
                                 ^
        +------------------------+------------------------+
        |                        |                        |
+---------------+        +---------------+        +---------------+
|     Phone     |        |    Tablet     |        |    Laptop     |
+---------------+        +---------------+        +---------------+
| + displayProduct()     | + displayProduct()     | + displayProduct()     |
| + getCategoryDetails() | + getCategoryDetails() | + getCategoryDetails() |
+---------------+        +---------------+        +---------------+
        ^
        |
+---------------+
|    IPhone     |
+---------------+
| + displayProduct()     |
| + getCategoryDetails() |
+---------------+

+-------------------+          +-------------------+          +-------------------+
|     Customer      |          |       Cart        |          |       Order       |
+-------------------+          +-------------------+          +-------------------+
| - name            |          | - products        |          | - orderId         |
| - phoneNumber     |          +-------------------+          | - customer        |
| - email           |          | + addProduct()    |          | - products        |
| - deliveryAddress |          | + removeProduct() |          | - totalAmount     |
+-------------------+          | + calculateTotal()|          | - paymentMethod   |
| + getCustomerSummary()       | + clearCart()     |          +-------------------+
+-------------------+          +-------------------+          | + getOrderSummary()|
                                                              +-------------------+
```

### 5.3 Activity Workflow Diagram

```
[ Launch App (MainActivity) ]
             |
             v
[ Category / Product List Screen ] ---> [ View Product Details Screen ]
             |                                        |
             +--------------------+-------------------+
                                  |
                                  v
                        [ Shopping Cart Screen ]
                                  |
                                  v
                        [ Checkout Form Screen ]
                                  | (Input Validation)
                                  v
                  [ Order Confirmation Screen ]
                                  |
                                  v
                     [ Return to MainActivity ]
```

---

## 6. Java OOP Implementation Details

### 6.1 Encapsulation
All fields across domain models (`Product`, `Customer`, `Cart`, `Order`) are declared `private`. Access and modifications are governed strictly through public getter and setter methods and explicit constructors.

### 6.2 Inheritance Hierarchy
The application implements a 3-level inheritance hierarchy:
1. `Product` (Parent Base Class)
2. `Phone extends Product` (Derived Child Class)
3. `IPhone extends Phone` (Derived Grandchild Class)
4. `Tablet extends Product` (Derived Child Class)
5. `Laptop extends Product` (Derived Child Class)

### 6.3 Method Overriding
Child classes override key behavior to produce specialized representations:
- `Product.displayProduct()` → `"Product: [Name] - K[Price]"`
- `Phone.displayProduct()` → `"Phone: [Name] - K[Price]"`
- `IPhone.displayProduct()` → `"iPhone: [Name] - K[Price]"`
- `Tablet.displayProduct()` → `"Tablet: [Name] - K[Price]"`
- `Laptop.displayProduct()` → `"Laptop: [Name] - K[Price]"`

### 6.4 Polymorphism in Action
Polymorphism is demonstrated dynamically during runtime:
- `Order.getOrderSummary()` iterates over an `ArrayList<Product>` containing instances of `Phone`, `IPhone`, `Tablet`, `Laptop`, and `Product`. Calling `product.displayProduct()` polymorphically executes the exact overridden method corresponding to each instance type.
- `CartActivity` displays cart items polymorphically using `product.displayProduct()`.
- `ProductData.demonstratePolymorphism()` provides a static utility verifying polymorphism across all catalog items.

---

## 7. Android UI & Shopping Architecture

### 7.1 Activities & Screens
1. **`MainActivity`:** Welcome banner, quick category navigation chips, and live cart counter button (`onResume()` synchronization).
2. **`CategoryActivity`:** Grid/List triggers for Phones, Tablets, Laptops, Audio, Smart TVs, Smart Watches, and All Products.
3. **`ProductListActivity`:** Dynamic product card rendering with image, name, price in Kina, description snippet, and "View Details" button.
4. **`ProductDetailsActivity`:** Full product layout with high-resolution image, category tag, full description, price, and "Add to Cart" button.
5. **`CartActivity`:** Itemized list of added products (polymorphically rendered), running subtotal/total calculation, and "Checkout" button.
6. **`CheckoutActivity`:** Validated form capturing Name, Phone, Email, Delivery Address, RadioGroup for Payment Method, Order Summary, and "Confirm & Place Order" button.
7. **`OrderConfirmationActivity`:** Final receipt displaying generated Order ID (e.g., `FH-A1B2C3D4`), Order Date, Customer Info, Itemized List, Payment Method, and Total Paid.

---

## 8. Testing & Debugging Report

Automated unit tests were implemented under `app/src/test/java/com/fonehaus/app/` using JUnit 4.

### 8.1 Unit Test Execution Matrix

| Test Suite Class | Test Method | Purpose | Result |
|---|---|---|---|
| `ProductPolymorphismTest` | `testInheritanceHierarchy()` | Verifies `instanceof` checks across `Product`, `Phone`, `IPhone`, `Tablet`, `Laptop` | **PASS** |
| `ProductPolymorphismTest` | `testPolymorphicDisplayProduct()` | Verifies dynamic method dispatch on `ArrayList<Product>` calling overridden `displayProduct()` | **PASS** |
| `ProductPolymorphismTest` | `testPolymorphicCategoryDetails()` | Verifies overridden `getCategoryDetails()` across child classes | **PASS** |
| `CartTest` | `testAddAndRemoveProduct()` | Verifies adding items, item count, total price calculation, and removal | **PASS** |
| `CartTest` | `testClearCart()` | Verifies clearing the cart resets item count to 0 and total price to K0.00 | **PASS** |
| `CustomerOrderTest` | `testCustomerCreation()` | Verifies encapsulation, getters, and `getCustomerSummary()` formatting | **PASS** |
| `CustomerOrderTest` | `testOrderCreationAndSummary()` | Verifies `Order` instantiation, ID generation, total match, and polymorphic summary formatting | **PASS** |
| `ExampleUnitTest` | `addition_isCorrect()` | Default baseline sanity test | **PASS** |

**Summary:** 8 tests executed, 8 passed, 0 failed, 0 skipped.

---

## 9. Deliverables Summary

- **Java Source Code:** Complete source code under `app/src/main/java/com/fonehaus/app/`.
- **XML Layouts:** Resource layouts under `app/src/main/res/layout/`.
- **Unit Test Suite:** Automated test files under `app/src/test/java/com/fonehaus/app/`.
- **Project Report:** Full technical report in `documentation/Fonehaus_Project_Documentation.md`.
- **Repository Documentation:** Updated `README.md` reflecting completed architecture.

---

## 10. Conclusion & Recommendations

The **Fone Haus Mobile Application** successfully satisfies all requirements specified in the IS223 Major Project criteria. The application transitions seamlessly from catalog navigation to product selection, shopping cart management, validated checkout, and order confirmation. The implementation provides clear evidence of core OOP concepts, particularly inheritance, method overriding, and polymorphism.

### Recommendations for Future Extensions
1. **Database Integration:** Integrate Firebase Firestore or SQLite Room database for persistent product storage and user accounts.
2. **Online Payment Gateway:** Connect payment APIs (such as BSP Pay or IPG) for live transaction processing.
3. **Push Notifications:** Implement Firebase Cloud Messaging (FCM) for order tracking status updates.
