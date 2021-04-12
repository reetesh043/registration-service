package com.gamesys.registration.exceptions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.Instant;

/**
 * The {@link RegistrationExceptionResolver} class manages and resolves various exceptions occurring in the Api.
 */
@ControllerAdvice
public class RegistrationExceptionResolver extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationExceptionResolver.class);


    /** Handle blacklisted payment card number exception
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(BlacklistedPaymentCardNumberException.class)
    public ResponseEntity<ErrorDetails> blacklistedPaymentCardNumberException(BlacklistedPaymentCardNumberException ex, WebRequest request) {
        logger.error("Error occurred" + ":" + ex.getLocalizedMessage());
        ErrorDetails errorDetails = new ErrorDetails(Instant.now().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
    }

    /** Handle duplicate username exception
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<ErrorDetails> handleDuplicateUsernameException(DuplicateUsernameException ex, WebRequest request) {
        logger.error("Error occurred" + ":" + ex.getLocalizedMessage());
        ErrorDetails errorDetails = new ErrorDetails(Instant.now().toString(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    /** Handle invalid user age exception
     *
     * @param ex
     * @param request
     * @return
     */

    @ExceptionHandler(InvalidAgeException.class)
    public ResponseEntity<ErrorDetails> invalidAgeException(InvalidAgeException ex, WebRequest request) {
        logger.error("Error occurred" + ":" + ex.getLocalizedMessage());
        ErrorDetails errorDetails = new ErrorDetails(Instant.now().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    /** Generic exception handler
     *
     * @param ex
     * @param request
     * @return
     */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> exceptionHandler(Exception ex, WebRequest request) {
        logger.error("Error occurred" + ":" + ex.getLocalizedMessage());
        ErrorDetails errorDetails = new ErrorDetails(Instant.now().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /** Handle Servlet Request Binding Exception
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Error occurred" + ":" + ex.getLocalizedMessage());
        ErrorDetails errorDetails = new ErrorDetails(Instant.now().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /** Handle Http Request Method Not Supported
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Error occurred" + ":" + ex.getLocalizedMessage());
        ErrorDetails errorDetails = new ErrorDetails(Instant.now().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /** Handle Method Argument Not Valid
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        String errorMessage = "";
        final BindingResult bindingResult = ex.getBindingResult();
        if (bindingResult != null) {
            final FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                if (fieldError.getDefaultMessage() != null) {
                    errorMessage = fieldError.getDefaultMessage();

                } else {
                    errorMessage = fieldError.getField();

                }
                logger.error("Error occurred" + ":" + errorMessage);
            }
        }
        ErrorDetails errorDetails = new ErrorDetails(Instant.now().toString(), errorMessage, request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /** Handle Http Media Type Not Supported
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(" Error occurred" + ":" + ex.getLocalizedMessage());
        ErrorDetails errorDetails = new ErrorDetails(Instant.now().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /** Handle Http Message Not Readable
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(" Error occurred" + ":" + ex.getLocalizedMessage());
        ErrorDetails errorDetails = new ErrorDetails(Instant.now().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}