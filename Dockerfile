FROM openjdk:16
ADD target/jumia.jar jumia.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "jumia.jar"]