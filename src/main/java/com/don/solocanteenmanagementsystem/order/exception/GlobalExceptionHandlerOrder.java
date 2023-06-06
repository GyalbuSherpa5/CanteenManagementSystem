package com.don.solocanteenmanagementsystem.order.exception;

import com.don.solocanteenmanagementsystem.customer.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerOrder {

    @ExceptionHandler(OrderIdNotFoundException.class)
    public ResponseEntity<ApiResponse> orderIdNotFound(OrderIdNotFoundException exception) {
        return new ResponseEntity<>(new ApiResponse("ORDER_ID_NOT_EXISTS", exception.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<ApiResponse> orderIdNotFound(OutOfStockException exception) {
        return new ResponseEntity<>(new ApiResponse("OUT_OF_STOCK", exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
