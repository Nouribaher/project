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

### ⚙️ `application.properties`

### properties
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
spring.jpa.properties.hibernate.jdbc.batch_size=100.

## 🔐 Advanced Configuration

This project includes performance tuning, secure authentication, and relational modeling for a scalable backend system.

### properties
spring.jpa.database=POSTGRESQL
spring.jpa.show-sql=true

spring.datasource.url=jdbc:postgresql://localhost:5431/demo
spring.datasource.username=demo_user
spring.datasource.password=demo_pass

spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=create

 
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

## 🗃️ SQL Schema & Sample Data
### Create tables
CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);

### CREATE TABLE topics 
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);'

### CREATE TABLE articles
(
  id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  content TEXT NOT NULL,
  user_id INTEGER REFERENCES users(id),
  topic_id INTEGER REFERENCES topics(id)
);

### CREATE TABLE comments 
(
  id SERIAL PRIMARY KEY,
  content TEXT NOT NULL,
  article_id INTEGER REFERENCES articles(id),
  user_id INTEGER REFERENCES users(id)
);

### Insert sample data

INSERT INTO users (username, password) VALUES ('john', 'password123');
INSERT INTO users (username, password) VALUES ('jane', 'password456');

INSERT INTO topics (name) VALUES ('Java');
INSERT INTO topics (name) VALUES ('Spring Boot');

INSERT INTO articles (title, content, user_id, topic_id) VALUES ('Intro to Java', 'Java is a programming language...', 1, 1);
INSERT INTO articles (title, content, user_id, topic_id) VALUES ('Spring Boot Basics', 'Spring Boot makes it easy...', 2, 2);

INSERT INTO comments (content, article_id, user_id) VALUES ('Great article!', 1, 2);
INSERT INTO comments (content, article_id, user_id) VALUES ('Very helpful, thanks!', 2, 1);

### Reactions (Bonus)

Design your own model to support likes/dislikes on articles and comments.



