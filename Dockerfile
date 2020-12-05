FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/eventapp-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} eventapp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/eventapp-0.0.1-SNAPSHOT.jar"]