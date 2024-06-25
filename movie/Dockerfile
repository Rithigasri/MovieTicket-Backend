# Use the official Gradle image to build the application
FROM gradle:7.3.3-jdk11 AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

# Use the official OpenJDK image to run the application
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/build/libs/movie-0.0.1-SNAPSHOT.jar /app/movie-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/movie-0.0.1-SNAPSHOT.jar"]
