FROM openjdk:11-jdk-slim
CMD mvn package
COPY target/restaurant-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]