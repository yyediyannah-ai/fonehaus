# Fonehaus

**Online Shopping Mobile Application — IS223 Major Project**
Papua New Guinea University of Technology | School of Business Studies | Bachelor of Business in Information Technology

## Project Title
Fonehaus: Design and Development of an Online Electronics Shopping Mobile Application using Java and Android Studio

## Student(s)
- William Bineke
- Lesley Yapa
- Matthias Virano
- Yedi Yannah Yuwom

## Project Description
Fonehaus is a functional Android mobile shopping application that allows customers to browse electronics products — such as smartphones, iPhones, tablets, laptops, audio gear, smart TVs, and smart watches — view product details, add items to a shopping cart, calculate order totals, enter customer delivery details, select payment methods, and complete a functional checkout process with order confirmation. The application is developed as part of the IS223 Major Project, demonstrating the application of Java object-oriented programming concepts to a real-world mobile business problem.

This is an academic prototype. It does not process real financial transactions.

## Application Objectives
- Analyse the requirements of an online electronics shopping application
- Design a mobile-friendly user interface using Android XML layouts
- Apply Java object-oriented programming principles throughout the application
- Implement classes and objects to represent products, customers, shopping carts, and orders
- Demonstrate inheritance, method overriding, and polymorphism
- Implement a functional shopping cart with total calculation and checkout
- Test and document the application's functionality with automated unit tests

## Development Tools
| Tool | Purpose |
|---|---|
| Android Studio | Development environment |
| Java | Programming language |
| Android SDK | Android development libraries |
| XML | User interface layout |
| JUnit 4 | Unit testing and OOP verification |
| Git / GitHub | Version control and collaboration |

## Main Features
- Home screen (`MainActivity`) with live cart counter and quick category chips
- Category navigation screen (`CategoryActivity`) for browsing Phones, Tablets, Laptops, Audio, Smart TVs, and Smart Watches
- Dynamic product listing (`ProductListActivity`) with category filtering and detail view triggers
- Product details view (`ProductDetailsActivity`) showing full specifications and add-to-cart functionality
- Interactive shopping cart (`CartActivity`) with item listing, removal, real-time total calculation, and checkout trigger
- Functional Checkout (`CheckoutActivity`) with input validation for Customer Name, Phone Number, Email, Delivery Address, and Payment Method Selection (Cash on Delivery, Mobile Money, Card)
- Order Confirmation screen (`OrderConfirmationActivity`) displaying Order ID, Customer Summary, Purchased Items, Payment Method, and Total Paid

## OOP Concepts Demonstrated
- **Classes & Objects** — `Product`, `Phone`, `IPhone`, `Tablet`, `Laptop`, `Customer`, `Cart`, `Order`, `CartManager`
- **Constructors** — Overloaded constructors across product hierarchy and domain models
- **Encapsulation** — Private fields with getter and setter methods across all data models
- **Deep Inheritance Hierarchy** — 
  - `Product` (Parent)
  - `Phone extends Product`
  - `IPhone extends Phone` (3-level inheritance chain)
  - `Tablet extends Product`
  - `Laptop extends Product`
- **Method Overriding** — Subclasses override `@Override public String displayProduct()` and `@Override public String getCategoryDetails()` for specialized subclass outputs
- **Polymorphism** — Polymorphic invocation of `displayProduct()` and `getCategoryDetails()` on `ArrayList<Product>` collections in `Order.getOrderSummary()`, `CartActivity`, and `ProductData.demonstratePolymorphism()`
- **Collections** — `ArrayList<Product>` used to manage catalog items, cart products, and order item histories

## Unit Testing
The project includes automated JUnit test suites in `app/src/test/java/com/fonehaus/app/`:
- `ProductPolymorphismTest.java` — Verifies inheritance hierarchy and polymorphic method dispatch.
- `CartTest.java` — Verifies adding/removing products, cart total calculation, and clearing the cart.
- `CustomerOrderTest.java` — Verifies Customer initialization and Order summary generation.

To run the unit tests:
```bash
./gradlew testDebugUnitTest
```

## Installation Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/yyediyannah-ai/fonehaus.git
   ```
2. Open the project in **Android Studio**.
3. Let Gradle sync automatically.
4. Run the app on an Android Emulator or physical device (minimum SDK: API 26 / Android 8.0).

## Course Information
- **Course:** IS223 Object-Oriented Programming Major Project
- **Programme:** Bachelor of Business in Information Technology
- **Academic Year:** 2026
