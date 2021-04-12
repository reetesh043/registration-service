package com.gamesys.registration.exceptions;


public class BlacklistedPaymentCardNumberException extends RuntimeException {

    private ErrorDetails errorDetails;

    public BlacklistedPaymentCardNumberException(ErrorDetails errorDetails, String message, Throwable cause) {
        super(message, cause);
        this.errorDetails = errorDetails;
    }

    public BlacklistedPaymentCardNumberException(String message) {
        super(message);
    }

    public BlacklistedPaymentCardNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
