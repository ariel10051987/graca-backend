
FROM openjdk:8-jdk-alpine

#RUN mkdir -p /var/wwww/html/

#COPY /var/www/html/target/api-0.0.1-SNAPSHOT.jar /var/www/html/api-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/app/java/target/cursomc-0.0.2-SNAPSHOT.jar"]
