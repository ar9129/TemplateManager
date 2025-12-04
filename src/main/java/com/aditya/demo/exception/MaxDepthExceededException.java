package com.aditya.demo.exception;

public class MaxDepthExceededException extends RuntimeException {
    public MaxDepthExceededException(String templateKey,  int maxDepth ) {
        super("Max recursion depth (" + maxDepth + ") exceeded for template: " + templateKey);
    }
}
