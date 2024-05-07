FROM openjdk:17

EXPOSE 8080

COPY ./build/libs/havryliuk-test-clear-solution-1.0-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "havryliuk-test-clear-solution-1.0-SNAPSHOT.jar"]