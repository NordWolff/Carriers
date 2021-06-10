FROM openjdk:13-alpine
Volume /tmp
ADD /target/*.jar carrier-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/carrier-0.0.1-SNAPSHOT.jar"]

# syntax=docker/dockerfile:1

# WORKDIR /app

# COPY .mvn/ .mvn
# COPY mvnw pom.xml ./
# RUN ./mvnw dependency:go-offline

# COPY src ./src

CMD ["./mvnw", "spring-boot:run"]
