package com.neon.banking.exceptions;

public class ModelConstraintErrorException extends RuntimeException {
    public ModelConstraintErrorException(String message) {
        super(message);
    }
}
