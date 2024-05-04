package com.havryliuk.test.users.advice;

import com.havryliuk.test.users.dto.response.GeneralErrorResponse;
import com.havryliuk.test.users.exception.DetailsResponseException;
import com.havryliuk.test.users.util.ResponseUtil;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.havryliuk.test.users.util.GlobalConstants.SERVER_ERROR_MESSAGE;
import static com.havryliuk.test.users.util.GlobalConstants.INCORRECT_JSON;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NotNull HttpHeaders headers,
                                                                  @NotNull HttpStatusCode status,
                                                                  @NotNull WebRequest request) {
        log.info(ex.getMessage());
        GeneralErrorResponse errorResponse = ResponseUtil.generateResponse(ex, status, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  @NotNull HttpHeaders headers,
                                                                  @NotNull HttpStatusCode status,
                                                                  @NotNull WebRequest request) {
        log.info(ex.getMessage());
        GeneralErrorResponse errorResponse = ResponseUtil.generateResponse(INCORRECT_JSON, status, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<GeneralErrorResponse> handleValidationExceptionExceptions(ValidationException ex,
                                                                                    WebRequest request) {
        log.info(ex.getCause().getMessage());
        if (ex.getCause() instanceof DetailsResponseException) {
            return handleUserRegistrationRestrictionExceptions((DetailsResponseException)ex.getCause(), request);
        }
        GeneralErrorResponse errorResponse = ResponseUtil.generateResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(DetailsResponseException.class)
    public ResponseEntity<GeneralErrorResponse>
            handleUserRegistrationRestrictionExceptions(DetailsResponseException ex, WebRequest request) {
        log.info(ex.getMessage());
        HttpStatusCode statusCode = ex.getStatusCode();
        GeneralErrorResponse errorResponse = ResponseUtil.generateResponse(ex, statusCode, request);
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
        log.error("Internal server error", ex);
        GeneralErrorResponse errorResponse = ResponseUtil
                .generateResponse(SERVER_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
