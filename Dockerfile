FROM maven:3.9.4-eclipse-temurin-17-alpine AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests -B

FROM eclipse-temurin:17-jre-alpine
RUN addgroup -g 1001 -S appuser && adduser -S appuser -G appuser -u 1001
WORKDIR /app
COPY --from=builder /app/target/tournament-management-1.0.0.jar app.jar
RUN chown appuser:appuser app.jar
USER appuser
EXPOSE 8080
CMD ["java", "-Dserver.port=${PORT:-8080}", "-Xmx450m", "-Xss512k", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=80.0", "-XX:+UseG1GC", "-jar", "app.jar"]
