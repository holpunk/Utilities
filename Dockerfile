# Use an official OpenJDK runtime as a parent image
FROM openjdk:23-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven Wrapper files and configuration
COPY mvnw ./
COPY .mvn .mvn

# Copy the pom.xml and source code
COPY pom.xml ./
COPY src ./src

# Build the project
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Expose port 8080
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/Utilities-0.0.1.jar"]