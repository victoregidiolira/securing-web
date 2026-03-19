# 🔐 Securing Web — Spring Security Portfolio Project

A full-stack web application built with Spring Boot demonstrating authentication, authorization, user registration, and production-ready best practices.

🌐 **Live Demo:** [securing-web-production.up.railway.app](https://securing-web-production.up.railway.app)

---

## 📋 Features

- User registration with form validation
- Login and logout with Spring Security
- Password encryption with BCrypt
- Protected routes (authenticated users only)
- Duplicate username detection
- Custom error pages (404, 403, 500)
- Database migrations with Flyway
- Responsive UI with Bootstrap 5

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 17, Spring Boot 4 |
| Security | Spring Security |
| Persistence | Spring Data JPA, Hibernate |
| Database | PostgreSQL |
| Migrations | Flyway |
| Frontend | Thymeleaf, Bootstrap 5 |
| Tests | JUnit 5, Mockito |
| Deploy | Railway |

---

## 🏗️ Architecture

The project follows a layered architecture with clear separation of concerns:

```
Controller  →  receives HTTP requests, handles validation feedback
Service     →  business logic (registration rules, password encoding)
Repository  →  database access via Spring Data JPA
Entity      →  JPA-mapped domain objects
```

---

## 🚀 Running Locally

### Prerequisites
- Java 17+
- Maven
- Docker

### Steps

1. Clone the repository:
```bash
git clone https://github.com/victoregidio/securing-web.git
cd securing-web
```

2. Start PostgreSQL with Docker:
```bash
docker run --name postgres-securingweb \
  -e POSTGRES_DB=securingweb \
  -e POSTGRES_USER=admin \
  -e POSTGRES_PASSWORD=admin \
  -p 5432:5432 \
  -d postgres
```

3. Run the application:
```bash
mvn spring-boot:run
```

4. Access at `http://localhost:8080`

---

## 🧪 Tests

Unit tests cover the `UserService` business logic using JUnit 5 and Mockito:

- Registration fails when username already exists
- Registration fails when password is too short
- Registration saves user when data is valid

```bash
mvn test
```

---

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/vito/securing_web/
│   │   ├── DataInitializer.java       # Seeds initial user on startup
│   │   ├── CustomUserDetailsService.java  # Loads user from database
│   │   ├── GlobalExceptionHandler.java    # Global error handling
│   │   ├── MvcConfig.java             # URL mappings
│   │   ├── UserController.java        # Registration endpoints
│   │   ├── UserEntity.java            # User JPA entity
│   │   ├── UserRepository.java        # Database access
│   │   ├── UserService.java           # Business logic
│   │   └── WebSecurityConfig.java     # Security configuration
│   └── resources/
│       ├── db/migration/
│       │   └── V1__create_users_table.sql
│       └── templates/
│           ├── home.html
│           ├── hello.html
│           ├── login.html
│           ├── register.html
│           └── error/
│               ├── 404.html
│               ├── 403.html
│               └── 500.html
└── test/
    └── java/com/vito/securing_web/
        └── UserServiceTest.java
```

---

## 🔒 Security

- Passwords are never stored in plain text — BCrypt hashing is applied before persistence
- Public routes: `/`, `/home`, `/register`, `/error/**`
- Protected routes: `/hello` and any other path requires authentication
- CSRF protection enabled by default via Spring Security

---

## 📝 License

This project was built for learning and portfolio purposes.
