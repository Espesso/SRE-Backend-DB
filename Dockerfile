# Build stage
FROM openjdk:23-ea-1 AS builder

WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies (cache layer)
RUN chmod +x ./mvnw && ./mvnw dependency:go-offline -B

# Copy source code
COPY src ./src

# Build application
RUN ./mvnw clean package -DskipTests -B

# Runtime stage
FROM openjdk:23-ea-1-slim

WORKDIR /app

# Copy jar from builder
COPY --from=builder /app/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]
