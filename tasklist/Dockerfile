# Stage 1: Build the app
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom and source code
COPY pom.xml .
COPY src ./src

# Build fat JAR
RUN mvn clean package -DskipTests

# Stage 2: Run the app
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy the fat JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port Render will use
EXPOSE 8081

# Run the app
ENTRYPOINT ["java","-jar","app.jar"]

