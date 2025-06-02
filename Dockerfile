# Базовий образ із Maven та JDK 17
FROM maven:3.9.6-eclipse-temurin-17

# Робоча директорія
WORKDIR /app

# Копіюємо всі файли у контейнер
COPY . .
# Базовий образ з Maven та JDK 17
FROM maven:3.9.4-eclipse-temurin-17

WORKDIR /app

# Копіюємо pom.xml і завантажуємо залежності
COPY pom.xml ./
RUN mvn dependency:go-offline

# Копіюємо все інше
COPY . .

# Збираємо проєкт
RUN mvn package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/instrument-panel-web-full-0.0.1-SNAPSHOT.jar"]

# Збірка проєкту
RUN mvn clean package -DskipTests

# Вказуємо порт і команду запуску
EXPOSE 8080
CMD ["java", "-jar", "target/instrument-panel-web-full-0.0.1-SNAPSHOT.jar"]
