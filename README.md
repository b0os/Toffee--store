# Toffee Store E-Commerce Platform (Java Application)

## Overview

Toffee Store is an e-commerce platform developed in Java offering a wide range of sweets including toffee, jelly, chocolate and more. This platform allows customers to conveniently browse and purchase items.

## Features

- **Catalog of Sweets**: Toffee Store offers a comprehensive catalog of sweets, allowing customers to explore various products.
- **User Registration and Authentication**: Customers can register and log in to their accounts, ensuring a personalized shopping experience.
- **Secure Registration**: Registration requires a valid email, password, and address, with verification via send OTP to email for more security.
- **Shopping Cart**: Logged-in users can add items to their shopping cart, review their selections, and proceed to checkout.
- **Ordering Options**: Customers can purchase items in sealed packages or by kilo, with flexibility in quantities.
- **Payment Methods**: Customers have various payment options including paying upon delivery (cash) and credit card payments (coming soon).

## System Architecture

The system architecture of Toffee Store consists of three main layers:

1. **Database Layer**: Utilizes a text file named `registration_info.txt` to store registration information efficiently.
2. **Backend Layer**: Implements the business logic of the application, handling user requests and interactions.
3. **Frontend Layer**: Provides an intuitive user interface for customers to browse products, place orders, and manage accounts.

## Included Library

The project includes the `javax.mail-1.6.2.jar` library, located in the `lib` folder. This library is essential for sending OTPs via email during the registration process. It enables secure registration by ensuring that users verify their email addresses before completing the registration process.

To ensure proper functionality, make sure to install or include this library in your project setup. If you're using an integrated development environment (IDE) like Eclipse or IntelliJ IDEA, you can add the library to your project dependencies. Alternatively, you can manually include the JAR file in your project's build path.

## Classes Overview

### AccountManager

- Manages user registration, authentication, and login.
- Checks the validity of email addresses and verifies if an email is already in use.
- Saves user registration information to the database file.

### Cart

- Represents the shopping cart functionality.
- Allows users to add items to the cart, calculate subtotals, fees, and totals.
- Displays the contents of the cart.

### Control

- Controls the flow of the application.
- Handles user interaction, registration, login, and shopping actions.
- Manages product display and ordering.

### Order

- Manages the order placement process.
- Displays order information, including subtotal, fees, and total.
- Handles the confirmation and finalization of orders.

### SendOTPEmail

- Sends OTPs (One Time Passwords) via email using the `javax.mail` library.
- Generates random OTPs for user verification during registration.

### User

- Represents user information, including name, email, address, phone, and password.

## Getting Started

To get started with the Toffee Store project, follow these steps:

1. Clone the repository to your local machine.
2. Make sure to include the `javax.mail-1.6.2.jar` library in your project's dependencies.
3. Run the `Main` class to start the application.


