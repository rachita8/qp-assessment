FROM eclipse-temurin:17-jdk-focal
ADD target/store-management-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "store-management-0.0.1-SNAPSHOT.jar"]