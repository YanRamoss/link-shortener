FROM openjdk:21-jdk-slim AS build

WORKDIR /app

COPY api/pom.xml ./

RUN apt-get update && apt-get install -y maven

COPY api/ ./

RUN mvn clean package -DskipTests

FROM openjdk:21-jre-slim

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 2100

CMD ["java", "-jar", "app.jar"]
