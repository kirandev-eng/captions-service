FROM eclipse-temurin:17-jdk-alpine
COPY target/xtp-data-service-0.0.1-SNAPSHOT.jar /app/xtp-data-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/xtp-data-service-0.0.1-SNAPSHOT.jar"]