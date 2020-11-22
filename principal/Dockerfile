FROM openjdk:8-jdk-alpine
ADD target/eventapp-2.4.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
