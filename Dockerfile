# Stage 1: Build with Maven wrapper
FROM eclipse-temurin:17-jdk AS build

# Set working directory
WORKDIR /app

# Copy Maven wrapper and necessary files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies (this helps cache layers)
RUN ./mvnw dependency:go-offline

# Copy all source code
COPY src src

# Build the JAR
RUN ./mvnw clean package -DskipTests

# --------------------------------------

# Stage 2: Run the app
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy the built JAR from previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (change this per service)
EXPOSE 4444

# Run the application
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]