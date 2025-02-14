FROM gradle:8.5-jdk17 AS builder
WORKDIR /app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew clean build

FROM openjdk:17-slim
WORKDIR /app
COPY --from=builder /app/build/libs/java-app-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8090
CMD ["java","-jar","app.jar"]
