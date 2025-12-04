package com.aditya.demo.exception;

public class CircularReferenceException extends RuntimeException {
    public CircularReferenceException(String templateKey) {
        super("Circular Reference for template:" + templateKey);
    }
}
