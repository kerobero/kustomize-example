FROM openjdk:8u222-jre
COPY build/libs/artifact/actuator-service-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
