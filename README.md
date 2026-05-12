# Store API

A modular and extensible **Store API** built with **Spring Boot**, designed to provide a clean, secure, and production-ready backend for an e-commerce application.

This project includes **JWT-based authentication**, **category and product management**, **stock handling**, and a fully functional **shopping cart system**.

---

## Overview

This repository represents the backend of a store management system.  
It was developed step-by-step with a focus on:

- secure authentication
- RESTful API design
---

## Features

### Authentication
- User registration
- JWT-based login
- Refresh token stored in **HttpOnly cookie**
- Access token generation from refresh token
- `/me` endpoint for retrieving authenticated user information

### Category Management
- Create categories
- Structured product grouping for future scalability

### Product Management
- Add products
- Retrieve all products
- Update product stock quantity
- Validation-friendly API design

### Cart Management
- Create a cart
- Add products to a cart
- Retrieve cart with cart items
- Cart and CartItem entity modeling

---

## Tech Stack

- **Java**
- **Spring Boot**
- **Spring Web**
- **Spring Security**
- **JWT**
- **REST API**
- **application.yaml** configuration

