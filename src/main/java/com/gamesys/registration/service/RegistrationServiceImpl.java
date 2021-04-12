package com.gamesys.registration.service;

import com.gamesys.registration.domain.UserEntity;
import com.gamesys.registration.domain.RegistrationRequest;
import com.gamesys.registration.repository.RegistrationRepository;
import com.gamesys.registration.validators.RegistrationConstraintValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class.
 */

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private RegistrationConstraintValidator registrationValidator;

    /** Validate the user and save the valid user to database as successful registration.
     *
     * @param request
     */
    @Override
    public void register(final RegistrationRequest request) {
        // check user details for blacklisted card number, invalid age and already registered username.
        registrationValidator.validateUserDetails(request);
        // save the user details to register successfully
        registrationRepository.save(createUserEntity(request));

    }

    private UserEntity createUserEntity(final RegistrationRequest request) {
       return  UserEntity.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .paymentCardNumber(request.getPaymentCardNumber())
                .dob(request.getDob())
                .build();
    }
}
