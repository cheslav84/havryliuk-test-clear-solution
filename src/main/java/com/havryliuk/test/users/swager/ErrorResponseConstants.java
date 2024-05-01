package com.havryliuk.test.users.swager;

public class ErrorResponseConstants {

    /**
     * User creation errors
     */

    public static final String USER_REQUIRED =
            """
                    {
                      "timestamp": "2024-05-01T14:02:18.641691800Z",
                      "statusCode": 400,
                      "error": "Bad request",
                      "message": "User data is required",
                      "path": "/v1/users"
                    }""";


    public static final String NOT_VALID_EMAIL =
            """
                    {
                      "timestamp": "2024-05-01T14:02:18.641691800Z",
                      "statusCode": 400,
                      "error": "Bad request",
                      "message": "Invalid email. Make sure that it contains only latin characters, numbers, underscores, hyphens and dots",
                      "path": "/v1/users"
                    }""";


    public static final String LONG_DATA =
            """
                    {
                      "timestamp": "2024-05-01T14:02:18.641691800Z",
                      "statusCode": 400,
                      "error": "Bad request",
                      "message": "Data should be maximum 32 characters long",
                      "path": "/v1/users"
                    }""";


    public static final String USER_AGE_RESTRICTION =
            """
                    {
                      "timestamp": "2024-05-01T14:02:18.641691800Z",
                      "statusCode": 403,
                      "error": "Forbidden",
                      "message": "The user should be {18} years old to register",
                      "path": "/v1/users"
                    }""";
}