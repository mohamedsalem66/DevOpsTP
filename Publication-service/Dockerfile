# Use a base image with OpenJDK 17
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/Gateway-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the port used by the application
EXPOSE 8084

# Command to run the application
CMD ["java", "-jar", "app.jar"]
