package com.don.solocanteenmanagementsystem.customer.exception;

import com.don.solocanteenmanagementsystem.customer.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExistException.class)
    public ResponseEntity<ApiResponse> customerAlreadyExist(CustomerAlreadyExistException exception) {
        return new ResponseEntity<>(new ApiResponse("CUSTOMER_ALREADY_EXISTS", exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerDoNotExistException.class)
    public ResponseEntity<ApiResponse> customerAlreadyExist(CustomerDoNotExistException exception) {
        return new ResponseEntity<>(new ApiResponse("CUSTOMER_DO_NOT_EXISTS", exception.getMessage()),
                HttpStatus.NOT_FOUND);
    }


}
