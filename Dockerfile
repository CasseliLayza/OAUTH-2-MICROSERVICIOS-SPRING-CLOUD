FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
ARG JAR_FILE=target/OAUTH-MICROSERVICIOS-SPRING-CLOUD-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} msvc_oauth.jar
EXPOSE 9100
CMD ["java", "-jar", "msvc_oauth.jar"]