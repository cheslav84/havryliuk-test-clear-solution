# Getting Started

### Reference Documentation
* [Swagger documentation](http://localhost:8080/swagger-ui/index.html)


### Preconditions
Age of user that is allowed to register is set in src/main/resources/config/application.yml file
(constants.allowed-age-to-register=18)
Some preconditions are taken into account that is not met in requirements:
- Each user has only one address;
- Address contains of fields: country, city, street, zipCode;
- There is no needs to find all users by address, so Address is embedded in User;
- Email validation performed by jakarta.validation.constraints.Email;
- Email is not unique as it is not required to be unique in requirements;
- All text fields has some length in order to not exceed database restrictions; 
- Optional fields also have some validation (e.g. phone, zipcode);
- Request for users by birthdate range returns partial information about users, 
but contains links to get the complete information separately for each user.


### CI
Simple CI is configured with GitHub Actions that builds app and runs application tests 
(see havryliuk-test-clear-solution\.github\workflows\ci.yml).


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

