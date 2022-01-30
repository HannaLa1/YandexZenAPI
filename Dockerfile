FROM openjdk:13
COPY /target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
