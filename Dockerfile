FROM openjdk:11
WORKDIR /app
RUN ./gradlew build
COPY . app.jar
CMD ["java","-jar","app.jar"]
