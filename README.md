# 🖼️ Image Search App

A full-stack application for image searching, built with **Spring Boot** (backend) and **Angular** (frontend).

---

## ⚙️ How to Run the Project Locally

Before you begin, make sure the following tools are installed on your machine:

- ✅ Java 17+
- ✅ Maven
- ✅ Node.js + npm
- ✅ Angular CLI  
  *(Install via: `npm install -g @angular/cli`)*

---

## 🌍 Set Up Environment Variables

Before running the application, make sure to set up your environment variables. There is no `.env` file in the GitHub repository, so you need to manually provide the following variables:

### Backend (Spring Boot)

1. Create a `.env` file in the `be/` folder (or set them directly in your system environment).
2. Add the necessary environment variables, such as database URLs, JWT keys, etc.

Example:

```env
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/postgres?currentSchema=cerebrica
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=PaSSword22
JWT_SECRET_KEY=your_jwt_secret_key_here
