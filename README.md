# Storefront & Warehouse Microservices

[🇧🇷 Leia em português](./README.pt-BR.md)

## 🧩 Overview
This project consists of two Spring Boot microservices — **Storefront** and **Warehouse** — communicating asynchronously through **RabbitMQ**.

It demonstrates an event-driven architecture using message queues to handle order creation and stock updates efficiently.

## 🚀 Architecture
- **Storefront**: Responsible for order creation and sending order events to RabbitMQ.
- **Warehouse**: Listens to the queue and updates stock levels accordingly.
- **RabbitMQ**: Acts as the message broker between services.

## ⚙️ Technologies

- Java 21
- Spring Boot 3.x
- Spring AMQP
- RabbitMQ
- Gradle

## ▶️ Running the Services
Make sure RabbitMQ is installed and running locally. Then, run each service separately:

```bash
cd storefront
./gradlew bootRun
```

```bash
cd warehouse
./gradlew bootRun
```

## 📦 Message Flow
Storefront publishes an order message to RabbitMQ.
Warehouse consumes the message and updates the stock.
Both log their activity to the console.

## 🧠 Learning Goals
Understand asynchronous communication between microservices.
Configure and use RabbitMQ with Spring Boot.
Apply clean separation between producer and consumer.

## 👨‍💻 Author
Hamilton Gonçalves Junior — Software Developer Student
