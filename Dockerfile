# Example Dockerfile
FROM openjdk:23-jdk-slim
COPY . /app
WORKDIR /app
RUN ./mvnw clean package -DskipTests
EXPOSE 8080
CMD ["java", "-jar", "target/Utilities-0.0.1.jar"]
