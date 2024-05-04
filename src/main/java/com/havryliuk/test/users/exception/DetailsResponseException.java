package com.havryliuk.test.users.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class DetailsResponseException extends ResponseStatusException {

    private final String property;
    public DetailsResponseException(HttpStatus httpStatus, String cause, String property) {
        super(httpStatus, cause);
        this.property = property;
    }

}
