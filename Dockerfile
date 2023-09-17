# syntax=docker/dockerfile:1

FROM gradle:latest AS BUILD
WORKDIR /usr/app/
COPY . .
RUN gradle build
RUN echo $(ls)

FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/*.jar
RUN touch .env
COPY build/libs/*.jar app.jar
RUN echo $(ls)
ENTRYPOINT ["java","-jar","/app/"]
EXPOSE 8080
