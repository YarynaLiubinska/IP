# 1. Вихідний образ з Maven + JDK
FROM maven:3.9.6-eclipse-temurin-17 AS build

# 2. Додай проєкт
WORKDIR /app
COPY . .

# 3. Збірка JAR-файлу
RUN mvn clean package -DskipTests

# 4. Тепер запустимо JAR з мінімального образу
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Скопіюй JAR-файл з попереднього кроку
COPY --from=build /app/target/*.jar app.jar


# Запуск
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
