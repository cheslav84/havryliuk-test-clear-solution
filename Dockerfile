FROM openjdk:17

EXPOSE 8080
WORKDIR /usr/app
COPY - from=build /usr/app/target/havryliuk-test-clear-solution-1.0-SNAPSHOT.jar /usr/app/


ENTRYPOINT ["java", "-jar", "havryliuk-test-clear-solution-1.0-SNAPSHOT.jar"]
