package com.don.solocanteenmanagementsystem.fooditem.exception;

import com.don.solocanteenmanagementsystem.customer.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerFoodItem {

    @ExceptionHandler(FoodItemAlreadyExistException.class)
    public ResponseEntity<ApiResponse> foodItemAlreadyExist(FoodItemAlreadyExistException exception) {
        return new ResponseEntity<>(new ApiResponse("FOOD_ITEM_ALREADY_EXISTS", exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FoodItemDoNotExistException.class)
    public ResponseEntity<ApiResponse> foodItemDoNotExist(FoodItemDoNotExistException exception) {
        return new ResponseEntity<>(new ApiResponse("FOOD_ITEM_DO_NOT_EXISTS", exception.getMessage()),
                HttpStatus.NOT_FOUND);
    }

}
