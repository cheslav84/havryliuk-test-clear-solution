package com.havryliuk.test.users.util;

import com.havryliuk.test.users.dto.response.GeneralErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatusCode;
import org.springframework.validation.BindException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.stream.Collectors;

public class ResponseUtil {

    public static GeneralErrorResponse generateResponse(BindException ex, HttpStatusCode status, WebRequest request) {
        return GeneralErrorResponse.builder()
                .timestamp(Instant.now())
                .statusCode(status.value())
                .error(HttpReasonResolver.getReasonPhrase(status.value()))
                .message(generateMessage(ex))
                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                .build();
    }


    public static GeneralErrorResponse generateResponse(String message, HttpStatusCode status, WebRequest request) {
        return GeneralErrorResponse.builder()
                .timestamp(Instant.now())
                .statusCode(status.value())
                .error(HttpReasonResolver.getReasonPhrase(status.value()))
                .message(message)
                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                .build();
    }


    private static String generateMessage(BindException ex) {
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(". "));

    }
}