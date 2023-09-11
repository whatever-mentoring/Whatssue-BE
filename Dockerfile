# Base image
FROM openjdk:20-jdk

# Set working directory
WORKDIR /app

# Copy the JAR file from the build output directory to the container
COPY build/libs/*.jar Whatssue.jar

# Expose port (if your Spring Boot application uses a different port, update it here)
EXPOSE 8090

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "Whatssue.jar"]
