package com.havryliuk.test.users.advice;

import com.havryliuk.test.users.dto.response.GeneralErrorResponse;
import com.havryliuk.test.users.util.ResponseUtil;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import static com.havryliuk.test.users.swager.Constants.SERVER_ERROR_MESSAGE;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NotNull HttpHeaders headers,
                                                                  @NotNull HttpStatusCode status,
                                                                  @NotNull WebRequest request) {
        log.warn(ex.getMessage());
        GeneralErrorResponse errorResponse = ResponseUtil.generateResponse(ex, status, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<GeneralErrorResponse> handleValidationExceptionExceptions(ValidationException ex,
                                                                                            WebRequest request) {
        log.warn(ex.getCause().getMessage());
        ResponseStatusException cause = (ResponseStatusException)ex.getCause();//todo not safe think about it
        HttpStatusCode statusCode = cause.getStatusCode();
        GeneralErrorResponse errorResponse = ResponseUtil.generateResponse(cause.getReason(), statusCode, request);
        return new ResponseEntity<>(errorResponse, cause.getStatusCode());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
        log.error("Internal server error", ex);
        GeneralErrorResponse errorResponse = ResponseUtil
                .generateResponse(SERVER_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
