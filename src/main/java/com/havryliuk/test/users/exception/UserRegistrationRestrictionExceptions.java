package com.havryliuk.test.users.exception;

import org.springframework.http.HttpStatus;

public class UserRegistrationRestrictionExceptions extends DetailsException {
    public UserRegistrationRestrictionExceptions(String property, String cause) {
        super(HttpStatus.FORBIDDEN, cause, property);
    }
}
