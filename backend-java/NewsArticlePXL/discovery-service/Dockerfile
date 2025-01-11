FROM openjdk:21-slim

WORKDIR /app

COPY ./discovery-service/target/discovery-service-0.0.1-SNAPSHOT.jar discovery-service.jar

EXPOSE 8061

ENTRYPOINT ["java", "-jar", "discovery-service.jar"]