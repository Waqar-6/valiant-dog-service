FROM openjdk:17-jdk-slim

MAINTAINER wfarooq.com

COPY target/dog-service-0.0.1-SNAPSHOT.jar dog-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "dog-service-0.0.1-SNAPSHOT.jar"]