package com.neon.banking.exceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        ErrorDetail errorDetail = new ErrorDetail(new Date(), status.value(), "Validation Failed",
                ex.getBindingResult().toString());

        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameExistsException.class)
    public ResponseEntity handleUsernameExists(UsernameExistsException ex, WebRequest webRequest) {
        ErrorDetail errorDetail = new ErrorDetail(new Date(), 400, ex.getMessage(), ex.getMessage());

        return new ResponseEntity(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ModelConstraintErrorException.class)
    public ResponseEntity handleModelConstraints(ModelConstraintErrorException ex, WebRequest webRequest) {
        ErrorDetail errorDetail = new ErrorDetail(new Date(), 400, ex.getMessage(), ex.getMessage());

        return new ResponseEntity(errorDetail, HttpStatus.BAD_REQUEST);
    }

}
