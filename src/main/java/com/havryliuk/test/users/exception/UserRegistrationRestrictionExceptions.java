package com.havryliuk.test.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static com.havryliuk.test.users.valitator.ValidatorConstants.USER_AGE_ERROR_MESSAGE;

public class UserRegistrationRestrictionExceptions extends ResponseStatusException {
    public UserRegistrationRestrictionExceptions(String allowedUserAge) {
        super(HttpStatus.FORBIDDEN, String.format(String.format(USER_AGE_ERROR_MESSAGE, allowedUserAge)));
    }
}
