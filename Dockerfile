FROM openjdk:11
EXPOSE 8083
ADD target/spring-jwt-0.0.1-SNAPSHOT.jar   spring-jwt-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/spring-jwt-0.0.1-SNAPSHOT.jar"]
