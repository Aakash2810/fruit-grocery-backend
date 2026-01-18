# Woofers Fruit Grocery Backend

Woofers is a backend service for a fruit grocery platform.
The application focuses on secure authentication, inventory management, and a cart-based order checkout flow.

This project is intended to demonstrate practical backend design using Java and Spring Boot, covering authentication, transactional consistency, and clean architecture.


## Features

- User registration and login using JWT authentication
- Secure REST APIs with Spring Security
- Fruit inventory management
- Cart-based ordering supporting multiple fruits per order
- Transactional checkout to ensure inventory consistency
- Optimistic locking to prevent overselling
- Pricing handled using Strategy and Factory design patterns
- API documentation using Swagger (OpenAPI)

---

## Technology Stack

- Java 17
- Spring Boot 3.5.9
- Spring Security
- Spring Data JPA (Hibernate)
- MySQL
- JWT (JJWT)
- Maven
- Swagger (springdoc-openapi)



## Application Design

The application follows a layered architecture:

Controller → Service → Repository → Database

### Order Flow

User → Cart → Add Fruits → Checkout → Order

- Users can add multiple fruits to a cart
- Inventory is validated and deducted only during checkout
- Order creation and inventory updates are handled within a single transaction


## Domain Model

- User – represents an authenticated customer
- Fruit – represents an inventory item with available quantity
- Cart – temporary container holding user selected fruits
- CartItem – fruit and quantity stored in the cart
- Order – confirmed purchase created after checkout
- OrderItem – individual items associated with an order


## Security

- Stateless authentication using JWT
- Passwords stored using BCrypt hashing
- Swagger and authentication endpoints are publicly accessible
- All business APIs require authentication


## Running the Application

### Prerequisites

- Java 17
- Maven
- MySQL running locally

### Database Setup

```sql
CREATE DATABASE woofers;
```

### Application Configuration

application.yml

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/woofers
    username: root
    password: root
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

### Build and Run

```bash
mvn clean install
mvn spring-boot:run
```

## Design Patterns Used

- Factory Pattern for selecting pricing strategies
- Transaction management for checkout flow
- Optimistic locking for inventory consistency


## Further Improvements

- Role-based access control (admin and customer roles)
- Order history APIs
- Refresh token support
- Pagination and filtering for inventory
- Integration and unit test coverage




