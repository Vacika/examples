# Multi-Tenant Spring Data JPA Example

This project demonstrates how to implement multi-tenancy in a Spring Boot application using Spring Data JPA with separate databases for each tenant.

## Table of Contents
- [Introduction](#introduction)
- [Architecture](#architecture)
- [Setup](#setup)
- [Running the Application](#running-the-application)
- [Testing](#testing)
- [Docker Compose](#docker-compose)

## Introduction

In the evolving landscape of software development, scalability and efficient resource management are paramount. One of the most effective ways to achieve these goals is through multi-tenancy, a design pattern where a single instance of an application serves multiple customers (tenants). This example focuses on using separate databases (data sources) for each tenant.

## Architecture

The architecture consists of:
- A `TenantContext` class to store tenant-specific information.
- A `MultiTenantDataSource` to dynamically switch between tenant databases.
- Separate configuration properties for each tenant's database.
- A Spring Security filter to extract tenant information from incoming requests.

## Setup

1. Clone the repository:
    ```sh
    git clone git@github.com:Vacika/examples.git
    cd multitenancy-example
    ```

2. Define your data sources in `application.yml`:
    ```yaml
    spring:
      datasource:
        default:
          url: jdbc:postgresql://localhost:5432/default_db
          username: default_user
          password: default_pass
        tenant:
          tenant1:
            url: jdbc:postgresql://localhost:5433/tenant1_db
            username: tenant1_user
            password: tenant1_pass
          tenant2:
            url: jdbc:postgresql://localhost:5434/tenant2_db
            username: tenant2_user
            password: tenant2_pass
      jpa:
        properties:
          hibernate:
            dialect: org.hibernate.dialect.PostgreSQLDialect
    ```

## Running the Application

1. Start the PostgreSQL databases using Docker Compose:
    ```sh
    docker-compose up -d
    ```

2. Run the Spring Boot application:
    ```sh
    ./mvnw spring-boot:run
    ```

## Testing

1. Run the tests using Maven:
    ```sh
    ./mvnw test
    ```

## Docker Compose

A `docker-compose.yml` file is provided to set up three PostgreSQL databases, one for each tenant, and a default database.

```yaml
version: '3.8'

services:
  postgres-default:
    image: postgres:13
    container_name: postgres-default
    environment:
      POSTGRES_DB: default_db
      POSTGRES_USER: default_user
      POSTGRES_PASSWORD: default_pass
    ports:
      - "5432:5432"
    volumes:
      - postgres-default-data:/var/lib/postgresql/data

  postgres-tenant1:
    image: postgres:13
    container_name: postgres-tenant1
    environment:
      POSTGRES_DB: tenant1_db
      POSTGRES_USER: tenant1_user
      POSTGRES_PASSWORD: tenant1_pass
    ports:
      - "5433:5432"
    volumes:
      - postgres-tenant1-data:/var/lib/postgresql/data

  postgres-tenant2:
    image: postgres:13
    container_name: postgres-tenant2
    environment:
      POSTGRES_DB: tenant2_db
      POSTGRES_USER: tenant2_user
      POSTGRES_PASSWORD: tenant2_pass
    ports:
      - "5434:5432"
    volumes:
      - postgres-tenant2-data:/var/lib/postgresql/data

volumes:
  postgres-default-data:
  postgres-tenant1-data:
  postgres-tenant2-data:
