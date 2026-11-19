# EzyBank Banking Platform

Java 21 / Spring Boot 4 learning microservices project.

Services:
- account-service :8080
- transaction-service :8081
- notification-service :8082

Flow:
POST /api/transactions/transfer -> transaction-service -> REST calls to account-service -> publishes transaction.completed to Kafka -> notification-service consumes it.

Account storage is intentionally in-memory for the first pass. PostgreSQL comes later.
