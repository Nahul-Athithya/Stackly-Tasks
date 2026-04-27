package com.simpletask.exception;

// Thrown when a Student is not found by ID
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
