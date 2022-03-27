# BUILD STAGE
FROM maven:3.8.2-openjdk-8 as build
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN mvn clean package -DskipTests

# RUNTIME STAGE
FROM openjdk:8-jdk-alpine
RUN mkdir /app
RUN apk add --no-cache bash
COPY --from=build /project/target/*.jar /app/app.jar
WORKDIR /app