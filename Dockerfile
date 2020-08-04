#
# Build stage
#
FROM maven:3.6.0-jdk-8-slim AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package

#
# Package stage
#
FROM openjdk:8-jdk-alpine

COPY --from=build /app/target/graca-1.0-SNAPSHOT.jar /usr/local/lib/graca-1.0-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/usr/local/lib/graca-1.0-SNAPSHOT.jar"]
