FROM maven:latest AS build

WORKDIR /app

COPY pom.xml /app/

COPY src /app/src

RUN mvn clean package -DskipTests

FROM openjdk:21-jdk

WORKDIR /app

COPY --from=build /app/target/link-shortener-0.0.1-SNAPSHOT.jar /app/link-shortener-0.0.1-SNAPSHOT.jar

COPY .env /app/.env

ENTRYPOINT ["java", "-jar", "/app/link-shortener-0.0.1-SNAPSHOT.jar"]