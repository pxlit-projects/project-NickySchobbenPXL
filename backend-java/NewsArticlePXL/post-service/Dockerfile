FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY ./post-service/target/*.jar postservice.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "postservice.jar"]