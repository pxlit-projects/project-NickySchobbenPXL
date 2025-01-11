FROM openjdk:21-slim

WORKDIR /app

COPY ./config-service/target/config-service-0.0.1-SNAPSHOT.jar config-service.jar

EXPOSE 8088

ENTRYPOINT ["java", "-jar", "config-service.jar"]