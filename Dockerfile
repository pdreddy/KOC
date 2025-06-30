# Multi-stage Dockerfile optimized for Render
FROM maven:3.9.4-eclipse-temurin-17-alpineAS builder

# Set working directory
WORKDIR /app

# Copy Maven files first (better caching)
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build application
RUN mvn clean package -DskipTests -B

# Production stage
FROM openjdk:17-jdk-slim

# Install curl for health checks
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Create non-root user
RUN groupadd -r appuser && useradd -r -g appuser appuser

# Set working directory
WORKDIR /app

# Copy JAR from builder stage
COPY --from=builder /app/target/tournament-management-1.0.0.jar app.jar

# Change ownership
RUN chown appuser:appuser app.jar

# Switch to non-root user
USER appuser

# Expose port
EXPOSE 8080

# Health check for Render
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run with optimized settings for Render free tier
CMD ["java", \
     "-Dserver.port=${PORT:-8080}", \
     "-Xmx400m", \
     "-Xss512k", \
     "-XX:+UseContainerSupport", \
     "-XX:MaxRAMPercentage=75.0", \
     "-XX:+UseG1GC", \
     "-XX:+UnlockExperimentalVMOptions", \
     "-XX:+UseCGroupMemoryLimitForHeap", \
     "-jar", \
     "app.jar"]
