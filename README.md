## Introduction

M-eFulfillment is the **CS Sales Fulfillment Service** for Apple CS Sales. It takes created orders and records their fulfillment so that downstream logistics, billing, and customer communication processes can operate on a clear view of which orders have moved into fulfillment. Clients submit an order identifier, and the service creates a corresponding fulfillment record linked to that order.

---

## Business Architecture

- **Purpose**: Track the transition of orders from “created” to “in fulfillment” (and beyond) so that operational teams and systems know which orders are being processed and can coordinate shipping, invoicing, and status updates.
- **Capabilities**: Given an order identifier, the service creates an order fulfillment record associated with that order. This provides a durable business record that fulfillment activities have started (or been registered) for a particular order.
- **Scope**: The service focuses on registering and persisting order fulfillment, not on managing the full logistics workflow (e.g. warehouse operations, shipment tracking). Order creation and pricing are assumed to be handled by upstream services; fulfillment status and related activities can be built on top of the records this service maintains.
- **Key concepts**: Orders (as the input from upstream), order fulfillments (as the business representation of fulfillment for a specific order), and the relationship between them where each fulfillment references an order.

---

## Data Architecture

- **Core entities**: Order Fulfillments. A fulfillment record has an identifier and a reference to the order it fulfills. Additional attributes (such as status, timestamps, and tracking identifiers) can be layered on as the fulfillment domain evolves.
- **Data store**: Relational store (PostgreSQL). Fulfillment records are stored with unique identifiers and foreign-key-like references to orders, enabling cross-service correlation between order and fulfillment data.
- **Data flow**: Write path: client submits an order identifier → the service creates a new fulfillment record with a generated identifier and associates it with the provided order id → the record is persisted and the newly created fulfillment is returned. Read behavior centers on returning the created fulfillment; other services can query or join by order id to derive a full order/fulfillment picture.
- **Caching**: Fulfillment-related data can be cached in memory (for example, using Caffeine) to speed up repeated reads of recently created fulfillment records, while PostgreSQL remains the system of record.

---

## Application Architecture

- **API layer**: REST over HTTP. The core capability is an endpoint that registers order fulfillment by accepting an order identifier in the request. The API contract is defined using OpenAPI and used for stub generation and interactive documentation (for example, via Swagger UI).
- **Service layer**: Implements the fulfillment registration flow: it validates the request, creates a new fulfillment entity with a unique identifier and a link to the provided order id, delegates persistence to the data access layer, and returns the created fulfillment record to the caller.
- **Data access layer**: Encapsulates persistence for order fulfillments via a repository/DAO abstraction, ensuring that the mapping between in-memory representations and database records is consistent and transactional.
- **Deployment unit**: Deployed as an independent service (for example, a Spring Boot application). Configuration such as database connection details, cache settings, and server port is externalized via configuration files and environment variables, and API documentation is served from the same process.

---

## Technology Architecture

- **Runtime**: Java 17.
- **Framework**: Spring Boot 3.2 for web/API endpoints, data access, caching, and developer tooling.
- **API**: REST with JSON payloads, defined by an OpenAPI contract and surfaced through SpringDoc and Swagger UI for API exploration and client generation.
- **Persistence**: JPA/Hibernate with PostgreSQL as the backing store, using connection pooling and PostgreSQL-specific dialect settings for reliable storage of fulfillment records.
- **Caching**: Spring Cache abstraction backed by Caffeine, with cache behavior (names, maximum size, time-to-live) defined in application configuration.
- **Build and operations**: Maven-based build that produces a runnable service artifact. The service runs as a single process with primary external dependencies on PostgreSQL and cache infrastructure; local development typically uses a containerized PostgreSQL instance with credentials aligned to the default configuration.

