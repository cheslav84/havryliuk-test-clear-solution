package com.havryliuk.test.users.swager;

import static com.havryliuk.test.users.util.GlobalConstants.BAD_REQUEST;
import static com.havryliuk.test.users.util.GlobalConstants.EMAIL_FIELD;
import static com.havryliuk.test.users.util.GlobalConstants.ERROR_PROPERTY;
import static com.havryliuk.test.users.util.GlobalConstants.FORBIDDEN;
import static com.havryliuk.test.users.util.GlobalConstants.INCORRECT_JSON;
import static com.havryliuk.test.users.util.GlobalConstants.LONG_PROPERTY_32;
import static com.havryliuk.test.users.util.GlobalConstants.DATA_NOT_FOUND;
import static com.havryliuk.test.users.util.GlobalConstants.NOT_FOUND;
import static com.havryliuk.test.users.util.GlobalConstants.NOT_VALID_EMAIL;
import static com.havryliuk.test.users.util.GlobalConstants.PROPERTY_REQUIRED;
import static com.havryliuk.test.users.util.GlobalConstants.SERVER_ERROR;
import static com.havryliuk.test.users.util.GlobalConstants.SERVER_ERROR_MESSAGE;
import static com.havryliuk.test.users.util.GlobalConstants.USERS_URL;
import static com.havryliuk.test.users.util.GlobalConstants.USERS_URL_ID;
import static com.havryliuk.test.users.util.GlobalConstants.USER_AGE_ERROR_MESSAGE;
import static com.havryliuk.test.users.util.GlobalConstants.USER_FIELD;

public class ErrorResponseConstants {

    private static final String TYPE_STAMP_EXAMPLE = "2024-05-01T14:02:18.641691800Z";

    /**
     * User creation errors
     */

    public static final String PROPERTY_REQUIRED_RESPONSE =
                    "{\n" +
                    "  \"timestamp\": \"" + TYPE_STAMP_EXAMPLE + "\",\n" +
                    "  \"title\": \"" + BAD_REQUEST + "\",\n" +
                    "  \"statusCode\": 400,\n" +
                    "  \"details\": [\n" +
                    "    {\n" +
                    "      \"property\": \"" + ERROR_PROPERTY + "\",\n" +
                    "      \"cause\": \"" + PROPERTY_REQUIRED + "\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"instance\": \"" + USERS_URL + "\n" +
                    "}";


    public static final String NOT_VALID_EMAIL_RESPONSE =
                    "{\n" +
                    "  \"timestamp\": \"" + TYPE_STAMP_EXAMPLE + "\",\n" +
                    "  \"title\": \"" + BAD_REQUEST + "\",\n" +
                    "  \"statusCode\": 400,\n" +
                    "  \"details\": [\n" +
                    "    {\n" +
                    "      \"property\": \"" + EMAIL_FIELD + "\",\n" +
                    "      \"cause\": \"" + NOT_VALID_EMAIL + "\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"instance\": \"" + USERS_URL + "\n" +
                    "}";

    public static final String LONG_PROPERTY_RESPONSE =
                    "{\n" +
                    "  \"timestamp\": \"" + TYPE_STAMP_EXAMPLE + "\",\n" +
                    "  \"title\": \"" + BAD_REQUEST + "\",\n" +
                    "  \"statusCode\": 400,\n" +
                    "  \"details\": [\n" +
                    "    {\n" +
                    "      \"property\": \"" + EMAIL_FIELD + "\",\n" +
                    "      \"cause\": \"" + LONG_PROPERTY_32 + "\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"instance\": \"" + USERS_URL + "\n" +
                    "}";

    public static final String USER_AGE_RESTRICTION_RESPONSE =
                    "{\n" +
                    "  \"timestamp\": \"" + TYPE_STAMP_EXAMPLE + "\",\n" +
                    "  \"title\": \"" + FORBIDDEN + "\",\n" +
                    "  \"statusCode\": 403,\n" +
                    "  \"details\": [\n" +
                    "    {\n" +
                    "      \"property\": \"" + USER_FIELD + "\",\n" +
                    "      \"cause\": \"" + USER_AGE_ERROR_MESSAGE + "\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"instance\": \"" + USERS_URL + "\n" +
                    "}";


    public static final String INCORRECT_DATA_RESPONSE =
                    "{\n" +
                    "  \"timestamp\": \"" + TYPE_STAMP_EXAMPLE + "\",\n" +
                    "  \"title\": \"" + BAD_REQUEST + "\",\n" +
                    "  \"statusCode\": 400,\n" +
                    "  \"details\": [\n" +
                    "    {\n" +
                    "      \"message\": \"" + INCORRECT_JSON + "\",\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"instance\": \"" + USERS_URL + "\n" +
                    "}";


    public static final String SERVER_ERROR_RESPONSE =
                    "{\n" +
                    "  \"timestamp\": \"" + TYPE_STAMP_EXAMPLE + "\",\n" +
                    "  \"title\": \"" + SERVER_ERROR + "\",\n" +
                    "  \"statusCode\": 500,\n" +
                    "  \"details\": [\n" +
                    "    {\n" +
                    "      \"message\": \"" + SERVER_ERROR_MESSAGE + "\",\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"instance\": \"" + USERS_URL + "\n" +
                    "}";



    /**
     * User updating errors
     */
    public static final String PROPERTY_REQUIRED_RESPONSE_ID =
                    "{\n" +
                    "  \"timestamp\": \"" + TYPE_STAMP_EXAMPLE + "\",\n" +
                    "  \"title\": \"" + BAD_REQUEST + "\",\n" +
                    "  \"statusCode\": 400,\n" +
                    "  \"details\": [\n" +
                    "    {\n" +
                    "      \"property\": \"" + ERROR_PROPERTY + "\",\n" +
                    "      \"cause\": \"" + PROPERTY_REQUIRED + "\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"instance\": \"" + USERS_URL_ID + "\n" +
                    "}";


    public static final String NOT_VALID_EMAIL_RESPONSE_ID =
            "{\n" +
                    "  \"timestamp\": \"" + TYPE_STAMP_EXAMPLE + "\",\n" +
                    "  \"title\": \"" + BAD_REQUEST + "\",\n" +
                    "  \"statusCode\": 400,\n" +
                    "  \"details\": [\n" +
                    "    {\n" +
                    "      \"property\": \"" + EMAIL_FIELD + "\",\n" +
                    "      \"cause\": \"" + NOT_VALID_EMAIL + "\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"instance\": \"" + USERS_URL_ID + "\n" +
                    "}";


    public static final String LONG_PROPERTY_RESPONSE_ID =
                    "{\n" +
                    "  \"timestamp\": \"" + TYPE_STAMP_EXAMPLE + "\",\n" +
                    "  \"title\": \"" + BAD_REQUEST + "\",\n" +
                    "  \"statusCode\": 400,\n" +
                    "  \"details\": [\n" +
                    "    {\n" +
                    "      \"property\": \"" + EMAIL_FIELD + "\",\n" +
                    "      \"cause\": \"" + LONG_PROPERTY_32 + "\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"instance\": \"" + USERS_URL_ID + "\n" +
                    "}";


    public static final String USER_AGE_RESTRICTION_RESPONSE_ID =
                    "{\n" +
                    "  \"timestamp\": \"" + TYPE_STAMP_EXAMPLE + "\",\n" +
                    "  \"title\": \"" + FORBIDDEN + "\",\n" +
                    "  \"statusCode\": 403,\n" +
                    "  \"details\": [\n" +
                    "    {\n" +
                    "      \"property\": \"" + USER_FIELD + "\",\n" +
                    "      \"cause\": \"" + USER_AGE_ERROR_MESSAGE + "\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"instance\": \"" + USERS_URL_ID + "\n" +
                    "}";

    public static final String USER_NOT_FOUND_RESPONSE_ID =
                    "{\n" +
                    "  \"timestamp\": \"" + TYPE_STAMP_EXAMPLE + "\",\n" +
                    "  \"title\": \"" + NOT_FOUND + "\",\n" +
                    "  \"statusCode\": 404,\n" +
                    "  \"details\": [\n" +
                    "    {\n" +
                    "      \"property\": \"" + USER_FIELD + "\",\n" +
                    "      \"cause\": \"" + DATA_NOT_FOUND + "\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"instance\": \"" + USERS_URL_ID + "\n" +
                    "}";


    public static final String INCORRECT_DATA_RESPONSE_ID =
                    "{\n" +
                    "  \"timestamp\": \"" + TYPE_STAMP_EXAMPLE + "\",\n" +
                    "  \"title\": \"" + BAD_REQUEST + "\",\n" +
                    "  \"statusCode\": 400,\n" +
                    "  \"details\": [\n" +
                    "    {\n" +
                    "      \"message\": \"" + INCORRECT_JSON + "\",\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"instance\": \"" + USERS_URL_ID + "\n" +
                    "}";


    public static final String SERVER_ERROR_RESPONSE_ID =
                    "{\n" +
                    "  \"timestamp\": \"" + TYPE_STAMP_EXAMPLE + "\",\n" +
                    "  \"title\": \"" + SERVER_ERROR + "\",\n" +
                    "  \"statusCode\": 500,\n" +
                    "  \"details\": [\n" +
                    "    {\n" +
                    "      \"message\": \"" + SERVER_ERROR_MESSAGE + "\",\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"instance\": \"" + USERS_URL_ID + "\n" +
                    "}";


}
