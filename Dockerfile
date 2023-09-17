# syntax=docker/dockerfile:1

FROM gradle:latest AS BUILD
WORKDIR /usr/app/
COPY . .
RUN gradle build
RUN echo $(ls)

FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/*.jar
RUN touch .env
COPY build/libs/*.jar /app/
RUN echo $(ls)
RUN echo $(pwd)
RUN cd /app
RUN echo $(ls)
RUN echo $(pwd)
ENTRYPOINT ["java","-jar","/app/app.jar"]
EXPOSE 8080
