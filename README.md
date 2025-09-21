# 📰 Dev News API

A backend RESTful API for a developer news platform built with **Spring Boot**, **Spring JPA**, and **PostgreSQL**. Users can create articles, comment on them, categorize by topics, and react with likes/dislikes.

## 🚀 Features

- CRUD operations for Articles, Comments, Topics
- Nested endpoints for article-specific comments and topics
- Reactions system for both articles and comments
- PostgreSQL integration with Hibernate ORM
- Clean JSON responses for Postman/cURL testing

## 🧠 Learning Objectives

- Understand the structure of a Spring Boot application
- Practice building and consuming REST APIs
- Model real-world relationships using JPA
- Interact with relational databases using Hibernate

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Gradle

## ⚙️ Setup

Update `src/main/resources/application.properties`:

## 🔐 Advanced Configuration

This project includes performance tuning, secure authentication, and relational modeling for a scalable backend system.

### ⚙️ `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/devnews
spring.datasource.username=postgres
spring.datasource.password=admin

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.properties.hibernate.id.new_generator_mappings=false
spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true
spring.jpa.properties.hibernate.default_batch_fetch_size=100
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.order_updates=true
spring.jpa.properties.hibernate.jdbc.batch_size=100
