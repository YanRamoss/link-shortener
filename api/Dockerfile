FROM maven:3.8.6-openjdk-21 AS build

WORKDIR /app

COPY api/pom.xml ./

RUN mvn dependency:go-offline

COPY api/ ./

RUN mvn clean package -DskipTests

FROM openjdk:21-jre-slim

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 2100

CMD ["java", "-jar", "app.jar"]