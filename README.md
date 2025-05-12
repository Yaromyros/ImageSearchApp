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
2. Add the necessary environment variables, such as AWS keys, etc.

Example:

```env
AWS_ACCESS_KEY=...............
AWS_SECRET_ACCESS_KEYE=.......
```

💡 If you're using IntelliJ IDEA, you can also set these variables in the run configuration.

### Frontend (Angular)

1. In the `fe/` folder, create a `.env` file or configure environment variables as needed.
2. Set any necessary variables for the Angular app, like the backend API URL.

Example:

```env
ANGULAR_APP_API_URL=http://localhost:8080/api
```

💡 If you're using Angular CLI, you can also use `angular.json` to set up environment-specific variables (e.g., for production or development).

---

## 1️⃣ Run the Backend (Spring Boot)

📁 Navigate to the `be/` folder:

```bash
cd be
```

▶️ Start the Spring Boot application:

```bash
./mvnw spring-boot:run
```

💡 Or open the project in IntelliJ IDEA and run the class with `@SpringBootApplication`.

---

## 2️⃣ Run the Frontend (Angular)

📁 Navigate to the `fe/` folder:

```bash
cd fe
```

📦 Install dependencies:

```bash
npm install
```

🚀 Start the Angular application:

```bash
ng serve
```

---

🌐 Done!
  - ✅ Backend is running at: `http://localhost:8080`
  - ✅ Frontend is available at: `http://localhost:4200`
```
