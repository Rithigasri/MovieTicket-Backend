# Use the official OpenJDK image as a parent image
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the Gradle build files
COPY build.gradle settings.gradle /app/

# Copy the application source
COPY src /app/src

# Install Gradle
RUN apt-get update && apt-get install -y gradle

# Build the application
RUN gradle build -x test

# Copy the built jar file to the container
COPY build/libs/*.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
