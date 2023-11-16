FROM openjdk:11
EXPOSE 8083
ADD target/management-1.0.jar   management.jar
ENTRYPOINT ["java","-jar","/management.jar"]
