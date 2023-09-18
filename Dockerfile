# syntax=docker/dockerfile:1

FROM gradle:latest
WORKDIR /usr/app/
COPY . .
RUN gradle build
RUN echo $(ls)

FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/*.jar
RUN touch .env
COPY build/libs/*.jar app/
ENTRYPOINT ["java","-jar","/app/quizApp-0.0.1.jar"]
EXPOSE 8080
