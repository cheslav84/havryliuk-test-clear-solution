package com.havryliuk.test.users.util;

import com.havryliuk.test.users.dto.response.GeneralErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.validation.BindException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.stream.Collectors;

import static com.havryliuk.test.users.swager.Constants.SERVER_ERROR_MESSAGE;

public class ResponseUtil {

//    public static GeneralErrorResponse createGeneralErrorTypeErrorResponse(int status) {
//        return GeneralErrorResponse.builder()
//                .dateTime(Instant.now())
//                .status(status)
//                .build();
//    }


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

//    public static GeneralErrorResponse generateResponse(Exception ex, HttpStatusCode status, WebRequest request) {
//        return GeneralErrorResponse.builder()
//                .timestamp(Instant.now())
//                .statusCode(status.value())
//                .error(status.toString())//todo create String descriptor
//                .message(SERVER_ERROR_MESSAGE)
//                .path(((ServletWebRequest)request).getRequest().getRequestURI())
//                .build();
//    }


    private static String generateMessage(BindException ex) {
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(". "));

    }
}


//{
//        "timestamp": "2019-01-17T16:12:45.977+0000",
//        "statusCode": 500,
//        "error": "Internal Server Error",
//        "message": "Error processing the request!",
//        "path": "/my-endpoint-with-exceptions"
//        }