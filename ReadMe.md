# Getting Started

### Reference Documentation
* [Swagger documentation](http://localhost:8080/swagger-ui/index.html)


### Preconditions
Some preconditions is taken into account that is not met in requirements:
- Each user has only one address;
- Address contains of fields: country, city, street, zipCode;
- Valid address zip code is from 5 to 10 any characters;
- Email can have only latin characters, numbers, underscores, hyphen and dots;
- Email is not unique as it not required in description;
- All text fields has some length in order not to exceed database restrictions; 
- Optional fields also have some validation.


### Guides
The following guides illustrate how to use some features concretely:


### Testcontainers support

This project uses [Testcontainers at development time](https://docs.spring.io/spring-boot/docs/3.2.5/reference/html/features.html#features.testing.testcontainers.at-development-time).

Testcontainers has been configured to use the following Docker images:


Please review the tags of the used images and set them to the same as you're running in production.

### Java practical test assignment  
The task has two parts:
1. Using the resources listed below learn what is RESTful API and what are the best practices to implement it
2. According to the requirements implement the RESTful API based on the web Spring Boot application: controller, responsible for the resource named Users.

#### Resources:
[RESTful API Design. Best Practices in a Nutshell](https://phauer.com/2015/restful-api-design-best-practices/)

[Error Handling for REST with Spring | Baeldung](https://www.baeldung.com/exception-handling-for-rest-with-spring)

[Testing in Spring Boot | Baeldung](https://www.baeldung.com/spring-boot-testing#unit-testing-with-webmvctest)

[Testing | Spring](https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#spring-mvc-test-server)


#### Requirements:
1. It has the following fields:

   1.1. Email (required). Add validation against email pattern

   1.2. First name (required)

   1.3. Last name (required)

   1.4. Birth date (required). Value must be earlier than current date
  
   1.5. Address (optional)

   1.6. Phone number (optional)
2. It has the following functionality:

   2.1. Create user. It allows to register users who are more than [18] years old. The value [18] should be taken from properties file.

   2.2. Update one/some user fields

   2.3. Update all user fields

   2.4. Delete user

   2.5. Search for users by birth date range. Add the validation which checks that “From” is less than “To”.  Should return a list of objects
3. Code is covered by unit tests using Spring
4. Code has error handling for REST
5. API responses are in JSON format
6. Use of database is not necessary. The data persistence layer is not required.
7. Any version of Spring Boot. Java version of your choice
8. You can use Spring Initializer utility to create the project: Spring Initializr

