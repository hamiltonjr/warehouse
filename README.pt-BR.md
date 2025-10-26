# Microsserviços Storefront & Warehouse

[🌎 Read in English](./README.md)

## 🧩 Visão Geral
Este projeto é composto por dois microsserviços Spring Boot — **Storefront** e **Warehouse** — que se comunicam de forma assíncrona através do **RabbitMQ**.

A arquitetura é orientada a eventos, demonstrando como o uso de filas de mensagens pode tornar o sistema mais escalável e desacoplado.

## 🚀 Arquitetura
- **Storefront**: Responsável por criar pedidos e enviar eventos de pedido ao RabbitMQ.
- **Warehouse**: Consome as mensagens da fila e atualiza o estoque.
- **RabbitMQ**: Atua como intermediário na comunicação entre os serviços.

## ⚙️ Tecnologias Utilizadas

- Java 21
- Spring Boot 3.x
- Spring AMQP
- RabbitMQ
- Gradle

## ▶️ Executando os Serviços
Certifique-se de que o RabbitMQ esteja instalado e rodando localmente. Depois, execute cada serviço separadamente:

```bash
cd storefront
./gradlew bootRun
```

```bash
cd warehouse
./gradlew bootRun
````
## 📦 Fluxo das Mensagens
O Storefront publica uma mensagem de pedido no RabbitMQ.
O Warehouse consome a mensagem e atualiza o estoque.
Ambos registram as ações no console.

## 🧠 Objetivos de Aprendizado
Compreender a comunicação assíncrona entre microsserviços.
Configurar e usar o RabbitMQ com Spring Boot.
Aplicar separação clara entre produtor e consumidor.

## 👨‍💻 Autor

Hamilton Gonçalves Junior — Estudante de Tecnologia