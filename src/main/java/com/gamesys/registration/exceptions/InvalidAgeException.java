package com.gamesys.registration.exceptions;


public class InvalidAgeException extends RuntimeException {

    private ErrorDetails errorDetails;

    public InvalidAgeException(ErrorDetails errorDetails, String message, Throwable cause) {
        super(message, cause);
        this.errorDetails = errorDetails;
    }

    public InvalidAgeException(String message) {
        super(message);
    }

    public InvalidAgeException(String message, Throwable cause) {
        super(message, cause);
    }
}

