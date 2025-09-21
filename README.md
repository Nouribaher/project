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

```properties
spring.jpa.database=POSTGRESQL
spring.jpa.show-sql=true

spring.datasource.url=jdbc:postgresql://localhost:5431/demo
spring.datasource.username=demo_user
spring.datasource.password=demo_pass

spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=create
## 📚 API Endpoints

### Articles

| Method | Path | Description |
|--------|------|-------------|
| GET    | `/articles` | Get all articles |
| GET    | `/articles/{id}` | Get article by ID |
| POST   | `/articles` | Create new article |
| PUT    | `/articles/{id}` | Update article |
| DELETE | `/articles/{id}` | Delete article |

### Comments

| Method | Path | Description |
|--------|------|-------------|
| GET    | `/articles/{articleId}/comments` | Get comments for article |
| GET    | `/comments?authorName={name}` | Get comments by author |
| POST   | `/articles/{articleId}/comments` | Add comment to article |
| PUT    | `/comments/{id}` | Update comment |
| DELETE | `/comments/{id}` | Delete comment |

### Topics

| Method | Path | Description |
|--------|------|-------------|
| GET    | `/topics` | Get all topics |
| GET    | `/articles/{id}/topics` | Get topics for article |
| POST   | `/topics` | Create topic |
| POST   | `/articles/{id}/topics` | Add topic to article |
| PUT    | `/topics/{id}` | Update topic |
| DELETE | `/topics/{id}` | Delete topic |
| DELETE | `/articles/{id}/topics/{topicId}` | Remove topic from article |
| GET    | `/topics/{id}/articles` | Get articles by topic |

### Reactions (Bonus)

Design your own model to support likes/dislikes on articles and comments.
