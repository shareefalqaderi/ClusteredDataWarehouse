FROM gradle:jdk17-alpine AS build
WORKDIR /app
COPY ../ /app
RUN gradle build --no-daemon
FROM openjdk:17-jdk-slim
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]