# Microservices Demo Project

This project is a microservices-based application designed to handle content submission and toxicity analysis. It leverages a distributed architecture with several specialized services communicating through an API Gateway, Service Discovery, and an asynchronous message broker.

## Architecture Overview

The system is composed of the following components:

### Core Services
- **APIGATEWAY**: The entry point for all client requests. It handles routing and authentication (JWT).
- **Service-discovery**: A Eureka server that allows microservices to find and communicate with each other dynamically.
- **UserService**: Manages user profiles and account information.
- **Content-submission-service**: Handles the submission of content and coordinates the validation and analysis process.
- **AI-Service**: Processes submitted content for analysis.
- **Toxicity-analysis-service**: A specialized service (likely Python-based) for detecting toxicity in content.

### Infrastructure
- **PostgreSQL**: Used as the primary database for both User and Content services.
- **RabbitMQ**: Acts as the message broker for asynchronous communication between services (e.g., between Content Submission and AI/Toxicity services).
- **Prometheus**: Monitors the health and performance of the services.
- **Grafana**: Provides visualization dashboards for the metrics collected by Prometheus.

## Technology Stack
- **Backend**: Java (Spring Boot), Python
- **Database**: PostgreSQL
- **Messaging**: RabbitMQ
- **Service Discovery**: Netflix Eureka
- **API Gateway**: Spring Cloud Gateway
- **Monitoring**: Prometheus & Grafana
- **Containerization**: Docker & Docker Compose

## Getting Started

### Prerequisites
- Docker and Docker Compose installed on your machine.
- An `.env` file with the following variables:
  - `EUREKA_URL`
  - `DB_USER_PASSWORD`
  - `DB_CONTENT_PASSWORD`

### Running the Application
To start the entire ecosystem, run:
```bash
docker-compose up -d
```

### Service Endpoints
- **API Gateway**: http://localhost:8088
- **Service Discovery**: http://localhost:8761
- **User Service**: http://localhost:8082
- **Content Submission Service**: http://localhost:8081
- **AI Service**: http://localhost:8083
- **Toxicity Analysis Service**: http://localhost:5000
- **Grafana**: http://localhost:3000
- **Prometheus**: http://localhost:9090

## Project Structure
- `/APIGATEWAY`: Gateway and Security logic.
- `/UserService`: User management logic.
- `/content-submission-service`: Content lifecycle management.
- `/AI-Service`: AI integration.
- `/toxicity-analysis-service`: Toxicity detection logic.
- `/Service-discovery`: Eureka server configuration.
