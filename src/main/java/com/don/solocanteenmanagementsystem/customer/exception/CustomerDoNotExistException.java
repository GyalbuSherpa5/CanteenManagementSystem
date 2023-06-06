package com.don.solocanteenmanagementsystem.customer.exception;

public class CustomerDoNotExistException extends RuntimeException {
    public CustomerDoNotExistException(String message) {
        super(message);
    }
}
