package com.gamesys.registration.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * The {@code ErrorDetails} class represents error object.
 * ErrorDetails object is constructed and sent as error response from api when an error is handled by {@link RegistrationExceptionResolver}
 */
@AllArgsConstructor
@Getter
@ToString
public final class ErrorDetails {
    private final String timestamp;
    private final String message;
    private final String details;

}
