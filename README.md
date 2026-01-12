
# 🚗 Quick Trip

A QuickTrip backend system similar to Uber/Ola 🚕 enabling cab bookings 📳, customer account management, and driver operations 🚔, with an admin panel for overseeing all users and cabs. Secured with JWT-based authentication 🔐 and role-based access control (RBAC), the project was built with Spring Boot and exposes 15+ REST APIs for all operations. It leverages Spring Data JPA for database interactions with MySQL ♻ and integrates Spring Mail 📩 to automatically send booking confirmation emails to customers. The entire API suite is thoroughly documented with Swagger 📰 for seamless testing and integration.

# ⚛ Architecture

Layered architecture is a design pattern where the application is divided into logical layers, and each layer has a specific responsibility. Each layer communicates only with the layer below it, not directly with other layers. The project follows layered architecture. The Controller layer handles HTTP requests, the Service layer contains business logic, and the Repository layer interacts with the database. Each layer has a single responsibility, which improves maintainability and scalability.”

![alt text](image-5.png)

# 💹 Entity Relationship

Project designed the database using an Entity Relationship model. Customer, Driver, Cab, and Booking are the core entities. A customer can have multiple bookings, each booking is associated with one driver and one cab, and a driver is linked to a single cab. Relationships are implemented using JPA annotations like @OneToMany and @ManyToOne.”

![alt text](image-1.png)


## 💥 All Documented API's

#### 🔐 Login API 

```http
  POST /auth/login
```


#### 👩‍👦 Customer API's

```http
  POST /customer/add
  GET /customer/get/profile-id/{emailId}
  GET /customer/get/All
  GET /customer/get/adult/
  UPDATE /customer/update
  DELETE /customer/delete/{emailId}
```


#### 🕺 Driver API's

```http
  POST /driver/add
  GET /driver/get/profile-id/{emailId}
  GET /driver/get/All
  UPDATE /driver/update
  DELETE /driver/delete/{emailId}
```


#### ✔ Booking API's

```http
  POST /booking/book/customer-id/{customerId}
  POST /booking/complete/driver/booking-id/{bookingId}
  POST /booking/cancel/customer/booking-id/{bookingId}
```


#### 🚖 Cab API's

```http
  POST /cab/register/driver-id/{driverId}
  GET /cab/get/cab-id/{cabId}
  GET /cab/get/all
```
## 🔄 Project Complete Workflow

**Customer Screen: 📱**  
- Add Rider ➡ Register Successfully 
- Login ➡ Get token (if authorized) ➡ Add token ➡ Manage Account such as update, delete etc

**Driver Screen: 📱**  
- Add Driver ➡ Register Successfully
- Login ➡ Get token (if authorized) ➡ Add Token ➡ Manage Account such as update, delete etc 

**Add Cab: 📱**  
- Login Driver ➡ Get token (if authorized) ➡ Add Token ➡ Add Cab with all necessary information ➡ Cab added successfully

**Book Cab: 📱**  
- Login Rider ➡ Get token (if authorized) ➡ Add Token ➡ Book Cab ➡ Booking successfully
- Login Rider ➡ Get token (if authorized) ➡ Add Token ➡ Cencel Cab  ➡ Cancel successfully


**When ride is completed: 📱**  
- Take booking id from rider  ➡  Insert Driver screen ➡ Ride  completed 




## 🔐 Security Feature

The most important model for securing the project is ***Spring Security***. Only Spring Security is not enough, so implement with ***JWT (JSON Web Token).***

***Authentication***

![alt text](image-2.png)

***Authorization***

![alt text](image-3.png)

**Authentication flow for different roles:**

![App Screenshot](https://github.com/user-attachments/assets/27573c54-cd4e-478e-801f-46f5d4430a93)




## 👀 Screenshots

**Description of the project:**

![alt text](image-4.png)


**All APIs:**

![App Screenshot](https://github.com/user-attachments/assets/0c0a92f8-9ec3-4717-890c-79ef3828c87e)

**Login APIs:**

![App Screenshot](https://github.com/user-attachments/assets/fb767d65-7a14-4b94-af74-20d0ec82fa88)

**Cab APIs:**

![App Screenshot](https://github.com/user-attachments/assets/27212230-0057-42ee-a39c-30228e5f6cef)

**JWT Token:**

![App Screenshot](https://github.com/user-attachments/assets/8c65b210-e74a-4fac-8d39-c04a789accbc)

**Token authorization:**

![App Screenshot](https://github.com/user-attachments/assets/0bb0f4e2-cf1b-4c01-a34b-0595a259c527)

**Request JSON payload:**

![App Screenshot](https://github.com/user-attachments/assets/5f6f70e1-9d36-4e93-a43f-7a336bd09749)

**Response JSON payload:**

![App Screenshot](https://github.com/user-attachments/assets/8710e8d5-5d01-4739-9a2f-c66c585e3e55)

**All Service classes:**

![App Screenshot](https://github.com/user-attachments/assets/4f988dec-e9da-4508-80e2-43df1705939c)




## 🕹 Technologies Used

**Spring Boot:** Rest APIs, Spring Data JPA, Spring Security Spring Mail etc

**Database:** MySQL

**Other:** Swagger Documentation, JWT token, Apache Tomcat



## ⏳ Pre-requisites:

Before running the project, make sure you have the following knowledge:

👩‍💻 Java 17+

🧠 Spring ecosytem (Spring Boot, Rest APIs, Spring Data JPA, Spring Security etc)

⚠ Spring security with JWT authetication and autherization

🎉 IDE (Spring Tool Suit, Eclipse, IntelliJ IDEA or any preferred IDE)

👯‍♀️ MySQL Database (for store all customer, driver, cab and booking info.)

🤔 Maven (for dependency management)

🎀 Swagger for documentation

✅ Lombok (for shorter code but with Annotation)

🔰 Devtool (for auto deployment)




## 🔗 Links
[![portfolio](https://img.shields.io/badge/github-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/IamPawan777)

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/pawan-bisht-a0578b201/)



## Have a safe journey! 😎


