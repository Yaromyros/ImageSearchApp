# Build stage
FROM maven:3.8.4-openjdk-17 AS builder

WORKDIR /app

# Кеш залежностей
COPY pom.xml .
RUN mvn dependency:go-offline

# Копіюємо весь проєкт
COPY . .

# Збираємо .jar файл
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17-jdk-slim

WORKDIR /app

# Копіюємо зібраний артефакт
COPY --from=builder /app/target/*.jar app.jar

# Запускаємо
ENTRYPOINT ["java", "-jar", "app.jar"]
