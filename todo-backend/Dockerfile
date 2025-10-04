# Use Eclipse Temurin JDK 21 with Alpine Linux as the base image
FROM eclipse-temurin:21-jdk-alpine

# Define a build argument for the JAR file
# During docker build, it will use the jar in target/ directory
ARG JAR_FILE=target/*.jar

# Copy the JAR file from the host to the container
# The jar will be renamed to app.jar inside the container
COPY ${JAR_FILE} app.jar

# Set the entrypoint for the container to run the Spring Boot app
ENTRYPOINT ["java", "-jar", "/app.jar"]
