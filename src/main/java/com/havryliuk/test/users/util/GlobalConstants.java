package com.havryliuk.test.users.util;

public class GlobalConstants {

    /**
     * URLs
     */
    public static final String USERS_URL = "/api/v1/users";
    public static final String USERS_URL_ID = "/api/v1/users/%s";


    /**
     * Header keys
     */
    public static final String LOCATION_HEADER = "location";


    /**
     * Regex patterns
     */
    public static final String PHONE_PATTERN = "^((\\+[1-9]{1}[0-9]{11})|([0]{1}[0-9]{9}))$";


    /**
     * Fields
     */
    public static final String DATA_FIELD = "data";
    public static final String USER_FIELD = "user";
    public static final String EMAIL_FIELD = "email";
    public static final String FIRST_NAME_FIELD = "firstName";
    public static final String LAST_NAME_FIELD = "lastName";
//    public static final String PHONE_NUMBER_FIELD = "phoneNumber";
    public static final String BIRTH_DATE_FIELD = "birthDate";
//    public static final String COUNTRY_FIELD = "country";
//    public static final String CITY_FIELD = "city";
//    public static final String STREET_FIELD = "street";
//    public static final String ZEEP_CODE_FIELD = "zipcode";


    /**
     * Field values
     */
    public static final String EMAIL = "domain@example.com";
    public static final String FIRST_NAME = "Firstname";
    public static final String LAST_NAME = "Lastname";
    public static final String PHONE_NUMBER = "+380961114545";
    public static final String BIRTH_DATE = "2006-05-01";
    public static final String COUNTRY = "Ukraine";
    public static final String CITY = "Kyiv";
    public static final String STREET = "Volodymyrska";
    public static final String ZEEP_CODE = "03455";


    /**
     * Swagger descriptions
     */
    public static final String USER_CREATED_HEADER_DESCRIPTION = "location: /v1/users/{id}";



    /**
     * Http responses
     */
    public static final String OK = "OK";
    public static final String CREATED = "Created";
    public static final String BAD_REQUEST = "Bad Request";
    public static final String FORBIDDEN = "Forbidden";
    public static final String NOT_FOUND = "Not Found";
    public static final String SERVER_ERROR = "Internal Server Error";


    /**
     * Error descriptions values
     */
    public static final String USER_AGE_ERROR_MESSAGE = "should be %d years old to register";
    public static final String NOT_VALID_EMAIL = "must be a well-formed address";
    public static final String PROPERTY_REQUIRED = "is required";
    public static final String DATA_NOT_FOUND = "data not found";
    public static final String LONG_PROPERTY_32 = "should be maximum 32 characters long";
    public static final String PROPERTY_SIZE_5_TO_10 = "should be from 5 to 10 characters long";
    public static final String INCORRECT_PHONE_NUMBER = "should be 10 digits long or '+' sign and 12 digits long";
    public static final String ERROR_PROPERTY = "%property";
    public static final String INCORRECT_JSON = "JSON parse error";
    public static final String SERVER_ERROR_MESSAGE = "Something went wrong. Please try again later.";
}
