FROM openjdk:17
EXPOSE 9092
COPY target/UserService-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]