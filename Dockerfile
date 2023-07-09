FROM gradle:latest AS BUILD
WORKDIR /usr/app/
COPY . .
RUN ./gradlew build
RUN echo $(ls)

#FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/*.jar
RUN touch .env
RUN echo $(ls)
COPY ./build/libs/*.jar app.jar
RUN echo $(ls)
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080