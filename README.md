# Woofers Fruit Grocery Backend

Woofers is a backend service for a fruit grocery platform.
The application focuses on secure authentication, inventory management, and a cart-based order checkout flow.

This project is intended to demonstrate practical backend design using Java and Spring Boot, with an emphasis on authentication, transactional consistency, and clean architecture.

---

## Features

- User registration and login using JWT authentication
- Secure REST APIs with Spring Security
- Fruit inventory management
- Cart-based ordering supporting multiple fruits per order
- Editable cart functionality:
  - Add items to cart
  - Update item quantity
  - Remove individual items
  - Clear entire cart
- Transactional checkout to ensure inventory consistency
- Optimistic locking to prevent overselling
- Pricing handled using Strategy and Factory design patterns
- Sensitive fields (e.g. passwords) are never exposed in API responses

---

## Technology Stack

- Java 17
- Spring Boot 3.5.9
- Spring Security
- Spring Data JPA (Hibernate)
- MySQL
- JWT (JJWT)
- Maven

---

## Application Design

The application follows a layered architecture:

Controller → Service → Repository → Database

### Order Flow

User → Cart → Add / Modify Items → Checkout → Order

- Users can freely modify their cart until checkout
- Inventory is validated and deducted only during checkout
- Order creation and inventory updates are handled within a single transaction
- Cart data is cleared after successful checkout

---

## Domain Model

- User – represents an authenticated customer
- Fruit – represents an inventory item with available quantity
- Cart – temporary container holding user-selected fruits
- CartItem – fruit and quantity stored in the cart
- Order – confirmed purchase created after checkout
- OrderItem – individual items associated with an order

---

## Security

- Stateless authentication using JWT
- Passwords stored using BCrypt hashing
- Password field is write-only and never returned in API responses
- Authentication endpoints are publicly accessible
- All business APIs require authentication

---

## Running the Application

### Prerequisites

- Java 17
- Maven
- MySQL running locally

### Database Setup

```sql
CREATE DATABASE woofers;
