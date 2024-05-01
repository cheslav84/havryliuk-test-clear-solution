package com.havryliuk.test.users.valitator;

public class ValidatorConstants {

        /**
         * Regex
         */
        public static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";


        /**
         * Error descriptions values
         */
//        public static final String USER_REQUIRED = "User data is required";
        public static final String USER_AGE_RESTRICTION = "The user is too young to be registered";
        public static final String USER_AGE_ERROR_MESSAGE = "The user should be %s years old to register";
        public static final String EMAIL_REQUIRED = "Email is required";
        public static final String FIRST_NAME_REQUIRED = "First name is required";
        public static final String LAST_NAME_REQUIRED = "Last name is required";
        public static final String BIRTH_DATE_REQUIRED = "Birth date is required";
        public static final String LONG_EMAIL = "Email should be maximum 32 characters long";
        public static final String NOT_VALID_EMAIL = "Invalid email. Make sure that it contains only latin " +
                "characters, numbers, underscores, hyphens and dots";
//        public static final String SIZE_32 = "%s size should be up to 32 chars long";


}
