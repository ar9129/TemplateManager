package com.aditya.demo.exception;

// Thrown when a referenced template does not exist or is disabled
public class TemplateNotFoundException extends RuntimeException {
    public TemplateNotFoundException(String templateKey) {
        super("Referenced template does not exist or is disabled: " + templateKey);
    }
}
