FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY ./gateway-service/target/*.jar gatewayservice.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "gatewayservice.jar"]