
# 🚗Texi Booking

A backend system similar to Uber/Ola 🚕 enabling cab bookings 📳, customer account management, and driver operations 🚔, with an admin panel for overseeing all users and cabs. Secured with JWT-based authentication 🔐 and role-based access control (RBAC), the project was built with Spring Boot and exposes 15+ REST APIs for all operations. It leverages Spring Data JPA for database interactions with MySQL ♻ and integrates Spring Mail 📩 to automatically send booking confirmation emails to customers. The entire API suite is thoroughly documented with Swagger 📰 for seamless testing and integration.
## All Documented API's

#### Login API

```http
  POST /auth/login
```


#### Customers API's

```http
  POST /customer/add
  GET /customer/get/profile-id/{emailId}
  GET /customer/get/All
  GET /customer/get/adult/
  UPDATE /customer/update
  DELETE /customer/delete/{emailId}
```


#### Drivers API's

```http
  POST /driver/add
  GET /driver/get/profile-id/{emailId}
  GET /driver/get/All
  UPDATE /driver/update
  DELETE /driver/delete/{emailId}
```


#### Booking API's

```http
  POST /booking/book/customer-id/{customerId}
  POST /booking/complete/driver/booking-id/{bookingId}
  POST /booking/cancel/customer/booking-id/{bookingId}
```


#### Cab API's

```http
  POST /cab/register/driver-id/{driverId}
  GET /cab/get/cab-id/{cabId}
  GET /cab/get/all
```
## Screenshots


![App Screenshot](https://github.com/user-attachments/assets/fe5bedab-1171-4c1a-a455-5e825889bb3a)


![App Screenshot](https://github.com/user-attachments/assets/0c0a92f8-9ec3-4717-890c-79ef3828c87e)


![App Screenshot](https://github.com/user-attachments/assets/fb767d65-7a14-4b94-af74-20d0ec82fa88)


![App Screenshot](https://github.com/user-attachments/assets/27212230-0057-42ee-a39c-30228e5f6cef)


![App Screenshot](https://github.com/user-attachments/assets/8c65b210-e74a-4fac-8d39-c04a789accbc)


![App Screenshot](https://github.com/user-attachments/assets/0bb0f4e2-cf1b-4c01-a34b-0595a259c527)


![App Screenshot](https://github.com/user-attachments/assets/5f6f70e1-9d36-4e93-a43f-7a336bd09749)


![App Screenshot](https://github.com/user-attachments/assets/8710e8d5-5d01-4739-9a2f-c66c585e3e55)


![App Screenshot](https://github.com/user-attachments/assets/4f988dec-e9da-4508-80e2-43df1705939c)




## Tech Stack

**Client:** React, Redux, TailwindCSS

**Server:** Node, Express

