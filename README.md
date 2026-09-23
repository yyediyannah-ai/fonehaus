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
Fonehaus is a prototype Android mobile shopping application that allows customers to browse electronics products — such as phones, accessories, and tablets — view product details, add items to a shopping cart, calculate order totals, and complete a simulated checkout process. The application is developed as part of the IS223 Major Project, demonstrating the application of Java object-oriented programming concepts to a real-world mobile business problem.

This is an academic prototype. It does not process real financial transactions.

## Application Objectives
- Analyse the requirements of an online electronics shopping application
- Design a mobile-friendly user interface using Android XML layouts
- Apply Java object-oriented programming principles throughout the application
- Implement classes and objects to represent products, customers, shopping carts, and orders
- Demonstrate inheritance, method overriding, and polymorphism
- Implement a functional shopping cart with total calculation
- Test and document the application's functionality

## Development Tools
| Tool | Purpose |
|---|---|
| Android Studio | Development environment |
| Java | Programming language |
| Android SDK | Android development libraries |
| XML | User interface layout |
| Android Emulator | Application testing |
| Git / GitHub | Version control and collaboration |

## Main Features
- Home screen with navigation to products, categories, and cart
- Product categories (e.g. Phones, Accessories, Tablets)
- Product listing with name, price, and category
- Product selection and add-to-cart functionality
- Shopping cart with subtotal and total calculation
- Simulated checkout screen (customer name, contact, delivery location, order summary)

## OOP Concepts Demonstrated
- **Classes & Objects** — `Product`, `Customer`, `Cart`, `Order`
- **Constructors** — used to initialise product and cart objects
- **Encapsulation** — private/protected fields with public methods
- **Inheritance** — `Product` as parent class; `Phone`, `Accessory`, `Tablet` as child classes
- **Method Overriding** — child classes override `displayProduct()` for specialised output
- **Polymorphism** — products of different subclasses handled through a common `Product` reference
- **Collections** — `ArrayList` used to manage product listings and cart items

## Project Structure
```
fonehaus/
├── app/                 # Android Studio project (Java source, XML layouts, resources)
├── screenshots/         # Application screenshots
├── documentation/       # Project report, UML diagrams, testing report
├── source-code/         # Supplementary source files (if applicable)
└── README.md
```

## Installation Instructions
1. Clone the repository:
   ```
   git clone https://github.com/yyediyannah-ai/fonehaus.git
   ```
2. Open the project in **Android Studio**.
3. Let Gradle sync automatically.
4. Run the app on an Android Emulator or a physical Android device (minimum SDK: API 26).

## Screenshots
*(Add screenshots of the Home, Products, Cart, and Checkout screens here once available — store image files in the `screenshots/` folder and reference them below.)*

```
![Home Screen](screenshots/home.png)
![Product List](screenshots/products.png)
![Shopping Cart](screenshots/cart.png)
![Checkout](screenshots/checkout.png)
```

## Project Limitation
This application is an academic prototype and does not implement real payment processing, banking transactions, or production-level authentication.

## Course Information
- **Course:** IS223 Object-Oriented Programming Major Project
- **Programme:** Bachelor of Business in Information Technology
- **Academic Year:** 2026
