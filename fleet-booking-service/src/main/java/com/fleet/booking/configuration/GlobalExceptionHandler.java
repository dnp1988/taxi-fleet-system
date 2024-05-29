package com.fleet.booking.configuration;

import com.fleet.booking.api.ErrorResponse;
import com.google.common.base.VerifyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {VerifyException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handle(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Void> handle(NoSuchElementException ex) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
