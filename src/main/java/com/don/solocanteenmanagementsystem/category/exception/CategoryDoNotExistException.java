package com.don.solocanteenmanagementsystem.category.exception;

public class CategoryDoNotExistException extends RuntimeException {
    public CategoryDoNotExistException(String message) {
        super(message);
    }
}
