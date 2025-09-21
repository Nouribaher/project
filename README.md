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

#### Exercise 1
Create an Article model and implement the above API.

### Comments
We want our visitors to be able to comment the different articles with a unique **id**, **body**, **authorName** (for the comment), and **article**
on which the comment was posted. Each article can have zero or more comments. 

Example JSON response when requesting a comment:

```json
{
    "id": 1,
    "body": "This article is very well written",
    "authorName": "John Smith",
    "article": {
        "id": 1,
        "title": "10 reasons to learn Spring",
        "body": "In this article I'll be listing 10 reasons why you should learn spring and use it in your next project...",
        "authorName": "John Smith"
    }
}

```
With the following endpoints:

| HTTP Method | HTTP Path | Action |
| ------------|-----------|--------|
| `GET`    | `/articles/{articleId}/comments`    | return all comments on article given by `articleId`. |
| `GET`    | `/comments?authorName={authorName}` | return all comments made by author given by `authorName`. |
| `POST`   | `/articles/{articleId}/comments`    | create a new comment on article given by `articleId`. |
| `PUT`    | `/comments/{id}`                    | update the given comment. |
| `DELETE` | `/comments/{id}`                    | delete the given comment. |

#### Exercise 2
Create a Comment model and implement the above API.

### Topics
We want to categorize our articles by topics. Each topic can be applied to zero or many articles and each article can have zero or many topics.

Example JSON response when requesting an article should now be:

```json
{
    "id": 1,
    "title": "10 reasons to learn Spring",
    "body": "In this article I'll be listing 10 reasons why you should learn spring and use it in your next project...",
    "authorName": "John Smith",
    "topics": [
        {
            "id": 1,
            "name": "backend"
        },
        {
            "id": 2,
            "name": "java"
        },
        {
            "id": 3,
            "name": "spring"
        }
    ]
}
```
Endpoints:

| HTTP Method | HTTP Path | Action |
| ------------|-----------|--------|
| `GET`    | `/topics` | return all topics. |
| `GET`    | `/articles/{articleId}/topics` | return all topics associated with article given by `articleId`. |
| `POST`   | `/articles/{articleId}/topics` | associate the topic with the article given by `articleId`. If topic does not already exist, it is created. |
| `POST`   | `/topics` | create a new topic. |
| `PUT`    | `/topics/{id}` | update the given topic. |
| `DELETE` | `/topics/{id}` | delete the given topic. |
| `DELETE` | `/articles/{articleId}/topics/{topicId}` | delete the association of a topic for the given article. The topic & article themselves remain. |
| `GET`    | `/topics/{topicId}/articles` | return all articles associated with the topic given by `topicId`. |

#### Exercise 3
Create a Topic model and implement the above API.

### Reactions

#### Exercise 4 (Bonus)
To make our application more interactive we might want to add the ability to add article and comment reactions (likes, dislikes, ...).
Go ahead and implement reactions in your application. You're free to choose how the model should look like so try to draw it out beforehand and think of what kind of relationship will the reactions have to the articles and comments respectively.




