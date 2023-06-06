package com.don.solocanteenmanagementsystem.category.exception;

import com.don.solocanteenmanagementsystem.customer.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerCategory {

    @ExceptionHandler(CategoryDoNotExistException.class)
    public ResponseEntity<ApiResponse> categoryDoNotExist(CategoryDoNotExistException exception) {
        return new ResponseEntity<>(new ApiResponse("CATEGORY_DO_NOT_EXIST", exception.getMessage()),
                HttpStatus.NOT_FOUND);
    }

}
