package com.don.solocanteenmanagementsystem.fooditem.exception;

public class FoodItemDoNotExistException extends RuntimeException {
    public FoodItemDoNotExistException(String message) {
        super(message);
    }
}
