package com.gamesys.registration.exceptions;


public class DuplicateUsernameException extends RuntimeException {

    private ErrorDetails errorDetails;

    public DuplicateUsernameException(ErrorDetails errorDetails, String message, Throwable cause) {
        super(message, cause);
        this.errorDetails = errorDetails;
    }

    public DuplicateUsernameException(String message) {
        super(message);
    }

    public DuplicateUsernameException(String message, Throwable cause) {
        super(message, cause);
    }
}