package com.havryliuk.test.users.util;

import com.havryliuk.test.users.dto.response.errors.ErrorResponseDetail;
import com.havryliuk.test.users.dto.response.errors.GeneralErrorResponse;
import com.havryliuk.test.users.exception.DetailsResponseException;
import org.springframework.http.HttpStatusCode;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class ResponseUtil {//todo use strategy

    public static GeneralErrorResponse generateResponse(String message, HttpStatusCode status, WebRequest request) {
        return GeneralErrorResponse.builder()
                .timestamp(Instant.now())
                .statusCode(status.value())
                .title(HttpReasonResolver.getReasonPhrase(status.value()))
                .details(List.of(ErrorResponseDetail.builder()
                        .message(message)
                        .build()))
                .instance(URIResolver.getWithParameters(request))
                .build();
    }


    public static GeneralErrorResponse generateResponse(BindException ex, HttpStatusCode status, WebRequest request) {
        return GeneralErrorResponse.builder()
                .timestamp(Instant.now())
                .statusCode(status.value())
                .title(HttpReasonResolver.getReasonPhrase(status.value()))
                .details(addDetails(ex))
                .instance(URIResolver.getWithParameters(request))
                .build();
    }


    public static GeneralErrorResponse generateResponse(DetailsResponseException ex, HttpStatusCode status, WebRequest request) {
        return GeneralErrorResponse.builder()
                .timestamp(Instant.now())
                .statusCode(status.value())
                .title(HttpReasonResolver.getReasonPhrase(status.value()))
                .details(addDetails(ex))
                .instance(URIResolver.getWithParameters(request))
                .build();
    }

    private static List<ErrorResponseDetail> addDetails(DetailsResponseException ex) {
        return List.of(createDetail(ex));
    }

    private static ErrorResponseDetail createDetail(DetailsResponseException ex) {
        return ErrorResponseDetail.builder()
                .property(ex.getProperty())
                .cause(ex.getReason())
                .build();
    }


    private static List<ErrorResponseDetail> addDetails(BindException ex) {
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ResponseUtil::createDetail)
                .collect(Collectors.toList());
    }


    private static ErrorResponseDetail createDetail(FieldError fieldError) {
        return ErrorResponseDetail.builder()
                .property(getProperty(fieldError))
                .cause(fieldError.getDefaultMessage())
                .build();
    }

    private static String getProperty(FieldError fieldError) {
        return fieldError.getField().substring(fieldError.getField().lastIndexOf('.') + 1);
    }

}