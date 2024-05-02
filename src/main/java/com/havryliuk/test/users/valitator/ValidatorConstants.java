package com.havryliuk.test.users.valitator;

public class ValidatorConstants {

        /**
         * Regex
         */
        public static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        public static final String PHONE_PATTERN = "^((\\+[1-9]{1}[0-9]{11})|([0]{1}[0-9]{9}))$";


        /**
         * Error descriptions values
         */
        public static final String USER_AGE_ERROR_MESSAGE = "should be %s years old to register";
        public static final String PROPERTY_REQUIRED = "is required";
        public static final String LONG_PROPERTY_32 = "should be maximum 32 characters long";
        public static final String INCORRECT_PHONE_NUMBER = "Phone number should be 10 digits long or + sign and 12 digits long";
        public static final String NOT_VALID_EMAIL = "Invalid email. Make sure that it contains only latin " +
                "characters, numbers, underscores, hyphens and dots";

}
