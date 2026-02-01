# Java 17 base image
FROM eclipse-temurin:17-jdk-alpine

# App jar location
WORKDIR /app

# Copy jar file
COPY target/wallet-service-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8090

# Run app
ENTRYPOINT ["java","-jar","app.jar"]
