package com.gamesys.registration.controller;

import com.gamesys.registration.domain.RegistrationRequest;
import com.gamesys.registration.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

/**
 * This is controller class for exposing RESTful endpoint. This class receives incoming request,
 * validates the headers, body and delegates the call to service implementation.
 */
@RestController
@Validated
public class RegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);


    @Autowired
    private RegistrationService registrationService;

    /** Method to register the valid user.
     *
     * @param registrationRequest
     */

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@Valid @RequestBody RegistrationRequest registrationRequest) {

        logger.info("Request received to register:  {} ", registrationRequest);
        registrationService.register(registrationRequest);
        logger.info("Registration completed successfully");
    }
}
