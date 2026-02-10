# Customer Feedback Management System – Backend

This is the **backend service** for the Customer Feedback Management System.  
It provides secure REST APIs for user authentication, feedback submission, product management, and admin dashboard operations.

The backend is built using **Spring Boot**, secured with **JWT authentication**, containerized using **Docker**, and deployed on **Microsoft Azure**.

---

## 🚀 Features

### 👤 Authentication & Authorization
- User registration and login
- JWT-based authentication
- Role-based access control (`ADMIN`, `USER`)
- Secure APIs using Spring Security

### 📝 Feedback Management
- Users can submit feedback with rating and comments
- Feedback is linked to both **User** and **Product**
- Timestamp (`createdAt`) stored for each feedback
- Admin can view all feedback and filter by rating

### 📦 Product Management (Admin)
- Add new products
- Update existing products
- Soft delete products (inactive instead of hard delete)
- Only active products are visible to users

### ☁️ Deployment
- Dockerized backend
- Deployed on Azure App Service
- MySQL database integration

---

## 🛠️ Tech Stack

| Layer            | Technology |
|------------------|------------|
| Backend          | Spring Boot 3 |
| Security         | Spring Security + JWT |
| ORM              | Spring Data JPA (Hibernate) |
| Database         | MySQL |
| Build Tool       | Maven |
| Containerization | Docker |
| Deployment       | Azure App Service |
| API Testing      | Postman |

---

## 📂 Project Structure

src/main/java/com/feedback
│
├── config → Security, JWT, CORS configuration
├── controller → REST Controllers
├── dto → Data Transfer Objects
├── entity → JPA Entities
├── repository → JPA Repositories
├── service → Business Logic
└── FeedbackBackendApplication.java


---

## 🔐 Security Overview

- JWT token generated on successful login
- Token sent in `Authorization` header:
- Stateless authentication (no server-side session)
- All APIs protected except `/api/auth/**`

---

## 📡 API Endpoints (Overview)

### Auth APIs
POST /api/auth/register
POST /api/auth/signin


### Product APIs (ADMIN)
POST /api/product/add
PUT /api/product/update/{id}
PUT /api/product/delete/{id}
GET /api/product/all


### Feedback APIs
POST /api/feedback/add (USER)
GET /api/feedback/all (ADMIN)
GET /api/feedback/rating/{rating}


---

## 🧩 Database Design (Entities)

- **User**
  - userId
  - fullName
  - email
  - password
  - role

- **Product**
  - productId
  - name
  - description
  - active (soft delete flag)

- **Feedback**
  - feedbackId
  - rating
  - comments
  - createdAt
  - user (ManyToOne)
  - product (ManyToOne)

---

## 🐳 Docker Setup

### Dockerfile (Example)
```dockerfile
FROM openjdk:17
WORKDIR /app
COPY target/feedback-backend.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]

docker build -t feedback-backend .

docker run -p 8085:8085 feedback-backend

## ☁️ Azure Deployment

The backend application is deployed on **Microsoft Azure** using **Docker-based App Service**.

### Deployment Steps

- Docker image created for the Spring Boot backend
- Azure App Service configured to run Docker containers
- Environment variables configured in Azure:
  - `SPRING_DATASOURCE_URL`
  - `SPRING_DATASOURCE_USERNAME`
  - `SPRING_DATASOURCE_PASSWORD`
- Docker container deployed to Azure App Service
- Application made accessible through Azure public URL
