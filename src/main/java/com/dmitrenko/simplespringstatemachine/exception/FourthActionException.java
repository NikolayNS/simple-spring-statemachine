package com.dmitrenko.simplespringstatemachine.exception;

public class FourthActionException extends RuntimeException {

    public FourthActionException() {
        super();
    }

    public FourthActionException(String message) {
        super(message);
    }

    public FourthActionException(String message, Throwable cause) {
        super(message, cause);
    }
}
