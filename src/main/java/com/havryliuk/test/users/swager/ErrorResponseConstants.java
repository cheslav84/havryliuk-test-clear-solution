package com.havryliuk.test.users.swager;

public class ErrorResponseConstants {

    /**
     * User creation errors
     */

    public static final String PROPERTY_REQUIRED =
            """
                    {
                      "timestamp": "2024-05-01T14:02:18.641691800Z",
                      "title": "Bad request",
                      "statusCode": 400,
                      "details": [
                        {
                          "property": "%property",
                          "cause": "is required"
                        }
                      ],
                      "instance": "/v1/users"
                    }""";


    public static final String NOT_VALID_EMAIL =
            """
                    {
                      "timestamp": "2024-05-01T14:02:18.641691800Z",
                      "title": "Bad request",
                      "statusCode": 400,
                      "details": [
                        {
                          "property": "email",
                          "cause": "Invalid email. Make sure that it contains only latin characters, numbers, underscores, hyphens and dots"
                        }
                      ],
                      "instance": "/v1/users"
                    }""";


    public static final String LONG_PROPERTY =
            """
                    {
                      "timestamp": "2024-05-01T14:02:18.641691800Z",
                      "title": "Bad request",
                      "statusCode": 400,
                      "details": [
                        {
                          "property": "%property",
                          "cause": "should be maximum 32 characters long"
                        }
                      ],
                      "instance": "/v1/users"
                    }""";


    public static final String USER_AGE_RESTRICTION =
            """
                    {
                      "timestamp": "2024-05-01T14:02:18.641691800Z",
                      "title": "Forbidden",
                      "statusCode": 403,
                      "details": [
                        {
                          "property": "user",
                          "cause": "should be %18 years old to register"
                        }
                      ],
                      "instance": "/v1/users"
                    }""";

    public static final String INCORRECT_DATA =
            """
                    {
                      "timestamp": "2024-05-01T14:02:18.641691800Z",
                      "title": "Bad request",
                      "statusCode": 400,
                      "details": [
                        {
                          "message": "JSON parse error"
                        }
                      ],
                      "instance": "/v1/users"
                    }""";


    public static final String SERVER_ERROR =
            """
                    {
                      "timestamp": "2024-05-01T14:02:18.641691800Z",
                      "title": "Internal Server Error",
                      "statusCode": 500,
                      "details": [
                        {
                          "message": "Something went wrong. Try please later"
                        }
                      ],
                      "instance": "/v1/users"
                    }""";
}