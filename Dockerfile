# Multi-stage Dockerfile optimized for Render
FROM maven:3.9.4-eclipse-temurin-17-alpine AS builder

# Set working directory
WORKDIR /app

# Copy Maven files first (better caching)
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build application
RUN mvn clean package -DskipTests -B

# Production stage - Use Eclipse Temurin instead of deprecated OpenJDK
FROM eclipse-temurin:17-jre-alpine

# Install curl for health checks
RUN apk add --no-cache curl

# Create non-root user
RUN addgroup -g 1001 -S appuser && \
    adduser -S appuser -G appuser -u 1001

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
     "-jar", \
     "app.jar"]