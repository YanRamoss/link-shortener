FROM openjdk:21-jdk-slim

RUN apt-get update && apt-get install -y curl unzip

RUN curl -fsSL https://archive.apache.org/dist/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz -o /tmp/maven.tar.gz \
    && tar -xvzf /tmp/maven.tar.gz -C /opt \
    && ln -s /opt/apache-maven-3.8.6/bin/mvn /usr/local/bin/mvn \
    && rm -f /tmp/maven.tar.gz

WORKDIR /app

COPY ./api/pom.xml .

RUN mvn dependency:go-offline

COPY ./api/src /app/src

RUN mvn clean package -DskipTests

CMD ["java", "-jar", "target/link-shortener-0.0.1-SNAPSHOT.jar"]