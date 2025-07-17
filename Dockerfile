# === Stage 1: Build ===
FROM maven:3.9.6-eclipse-temurin-17 as builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# === Stage 2: Run ===
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=builder /app/target/calorie-tracker-app-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

