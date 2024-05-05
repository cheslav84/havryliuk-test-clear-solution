package com.havryliuk.test.users.exception;

import org.springframework.http.HttpStatus;

import static com.havryliuk.test.users.util.GlobalConstants.DATA_NOT_FOUND;

public class NotFoundException extends DetailsResponseException {
    public NotFoundException(String property) {
        super(HttpStatus.NOT_FOUND, DATA_NOT_FOUND, property);
    }
}
