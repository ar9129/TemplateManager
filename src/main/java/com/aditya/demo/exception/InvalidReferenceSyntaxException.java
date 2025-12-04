package com.aditya.demo.exception;

// Thrown when the placeholder syntax is malformed
public class InvalidReferenceSyntaxException extends RuntimeException {
    public InvalidReferenceSyntaxException(String placeholder) {
        super("Malformed placeholder syntax: " + placeholder);
    }
}
