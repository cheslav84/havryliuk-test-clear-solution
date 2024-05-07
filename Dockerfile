FROM openjdk:17
EXPOSE 8080
ADD target/havryliuktest havryliuktest.jar
ENTRYPOINT ["java", "-jar", "havryliuk-test-clear-solution-1.0-SNAPSHOT.jar"]