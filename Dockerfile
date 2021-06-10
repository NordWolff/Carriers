FROM openjdk:13-alpine
Volume /tmp
ADD /target/*.jar carrier-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/carrier-0.0.1-SNAPSHOT.jar"]
