# 🌍 GlobalStream Payment Orchestrator

A Spring Boot microservice that implements a **Payment Orchestration Gateway** using the **Strategy Design Pattern** to intelligently route payment transactions through the most suitable Payment Service Provider (PSP) based on geographic region and payment method.

The project simulates how a global digital platform (GlobalStream) can optimize payment approval rates, reduce processing costs, and improve customer experience through smart payment routing.

---

## 🚀 Business Problem

Global digital platforms often experience lower payment authorization rates when all transactions are processed through a single payment provider.

Examples:

* Latin America users benefit from local processors such as PayU.
* African markets have better authorization rates through Flutterwave.
* North America and Europe are efficiently handled by Stripe.

Without intelligent routing:

* Higher payment failures
* Lower conversion rates
* Increased transaction costs
* Poor customer experience

This project solves the problem by introducing a centralized orchestration layer that automatically routes transactions to the optimal PSP.

---

## 🏗️ Architecture Overview

```text
┌─────────────────────┐
│   Checkout UI       │
│  (Buy Stars Page)   │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│ Payment Controller  │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│ Smart Routing Layer │
└──────────┬──────────┘
           │
 ┌─────────┼─────────┐
 ▼         ▼         ▼
PayU   Flutterwave  Stripe
(LatAm) (Africa)   (Global)

           │
           ▼
┌─────────────────────┐
│ H2 Database         │
│ Transaction Store   │
└─────────────────────┘
```

---

## ✨ Features

### Payment Orchestration

* Single API endpoint for all payment requests
* Unified payment processing workflow
* Extensible provider architecture

### Smart Routing

Automatically routes payments based on:

| Region                                     | Processor   |
| ------------------------------------------ | ----------- |
| Brazil, Mexico, Colombia, Argentina, Chile | PayU        |
| Nigeria, Kenya, South Africa               | Flutterwave |
| USA, Canada, UK, Germany                   | Stripe      |

### Strategy Pattern Implementation

* Clean separation of payment providers
* Easily add new PSP integrations
* Open/Closed Principle compliant

### Persistence

* Transaction records stored in H2 database
* Unified transaction history

### Frontend Simulation

* Interactive checkout UI
* Simulates GlobalStream "Buy Stars" flow
* Displays routing decisions in real time

---

## 🛠️ Technology Stack

### Backend

* Java 17+
* Spring Boot 3
* Spring Web
* Spring Data JPA
* H2 Database
* Lombok
* Maven

### Frontend

* HTML5
* CSS3
* Vanilla JavaScript

### Design Patterns

* Strategy Pattern
* Dependency Injection
* Service Layer Pattern

---

## 📂 Project Structure

```text
globalstream-orchestrator
│
├── controller
│   └── PaymentController
│
├── dto
│   ├── PaymentRequest
│   └── PaymentResponse
│
├── entity
│   └── TransactionRecord
│
├── repository
│   └── TransactionRepository
│
├── service
│   └── SmartRoutingService
│
├── strategy
│   ├── PaymentProviderStrategy
│   └── impl
│       ├── PayUStrategy
│       ├── FlutterwaveStrategy
│       └── StripeStrategy
│
└── resources
    ├── application.properties
    └── static
        └── index.html
```

---

## ⚙️ Smart Routing Logic

### PayU Routing

```java
countryCode.matches("BR|MX|CO|AR|CL")
```

### Flutterwave Routing

```java
countryCode.matches("NG|KE|ZA")
```

### Stripe Routing

```java
countryCode.matches("US|CA|UK|DE")
```

---

## 📡 API Endpoint

### Process Payment

```http
POST /api/v1/payments/process
```

### Request

```json
{
  "userId": "USER_1234",
  "amount": 8.50,
  "currency": "USD",
  "countryCode": "BR",
  "paymentMethod": "CARD"
}
```

### Success Response

```json
{
  "transactionId": "PAYU-a123456",
  "status": "APPROVED",
  "processorUsed": "PayU-LatAm",
  "message": "Processed locally via PayU rails"
}
```

### Failure Response

```json
{
  "status": "FAILED",
  "message": "No supported payment provider found"
}
```

---

## 💻 Running Locally

### Clone Repository

```bash
git clone https://github.com/your-username/globalstream-orchestrator.git

cd globalstream-orchestrator
```

### Build Project

```bash
mvn clean install
```

### Start Application

```bash
mvn spring-boot:run
```

Application will start at:

```text
http://localhost:8080
```

---

## 🖥️ Access the Application

### Checkout UI

```text
http://localhost:8080
```

### H2 Database Console

```text
http://localhost:8080/h2-console
```

Configuration:

```text
JDBC URL : jdbc:h2:mem:paymentdb
Username : sa
Password : (empty)
```

---

## 🧪 Sample Routing Scenarios

| Country | Payment Method | Routed To   |
| ------- | -------------- | ----------- |
| Brazil  | CARD           | PayU        |
| Nigeria | MPESA          | Flutterwave |
| Kenya   | CARD           | Flutterwave |
| USA     | CARD           | Stripe      |
| Germany | CARD           | Stripe      |

---

## 📸 Demo Flow

1. Open the GlobalStream Checkout UI.
2. Select a region.
3. Choose a payment method.
4. Click **Pay $8.50**.
5. The request is sent to the orchestration gateway.
6. Smart Routing selects the best PSP.
7. Transaction is stored in H2.
8. Success receipt displays the selected processor.

---

## 🔮 Future Enhancements

* Docker Support
* Docker Compose
* PostgreSQL Integration
* Redis Caching
* Kafka Event Streaming
* Circuit Breaker (Resilience4j)
* Payment Retry Mechanism
* Webhook Processing
* PSP Health Monitoring
* Real Payment Gateway Integrations
* Kubernetes Deployment
* Observability with Prometheus & Grafana

---

## 📚 Key Concepts Demonstrated

* Microservices Architecture
* Payment Orchestration
* Smart Transaction Routing
* Strategy Design Pattern
* REST API Development
* Spring Boot Best Practices
* Transaction Persistence
* Frontend–Backend Integration

---

## 👨‍💻 Author

**Saravanakumar M**

Java Backend Developer

Skills:
Java • Spring Boot • REST APIs • Microservices • AWS • SQL • CI/CD

---
