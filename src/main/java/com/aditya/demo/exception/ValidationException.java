package com.aditya.demo.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String templateId) {
        super("Invalid or blank template ID inside reference: " + templateId);
    }
}
