FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]