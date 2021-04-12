package com.gamesys.registration.validators;

import com.gamesys.registration.domain.UserEntity;
import com.gamesys.registration.domain.RegistrationRequest;
import com.gamesys.registration.exceptions.BlacklistedPaymentCardNumberException;
import com.gamesys.registration.exceptions.DuplicateUsernameException;
import com.gamesys.registration.exceptions.InvalidAgeException;
import com.gamesys.registration.repository.RegistrationRepository;
import com.gamesys.registration.service.BlacklistedIINService;
import com.gamesys.registration.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/*
 * Class to perform different constraint validation on the request object.
 */

@Component
public class RegistrationConstraintValidator {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationConstraintValidator.class);

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private BlacklistedIINService blacklistedIINService;

    @Value("${ageLimit}")
    private int ageLimit;

    @Value("${iinLength}")
    private int iinLength;

    /**
     * Validates request body for blacklisted card number, invalid age of user
     * and if username already registered to the system.
     *
     * @param request - registration request
     */
    public void validateUserDetails(final RegistrationRequest request) {
        logger.info("validate the registration request");
        checkUserAge(request.getDob());
        checkDuplicateUsername(request.getUsername());
        checkBlackListedPaymentCardNumber(request.getPaymentCardNumber());
        logger.info("registration request validation is successfully done");
    }

    private void checkBlackListedPaymentCardNumber(final String paymentCardNumber) {
        final Integer cardNumber = Integer.parseInt(paymentCardNumber.substring(0, iinLength));
        List<Integer> list= blacklistedIINService.getBlackListedIIN();
        boolean found = list.stream().anyMatch(x -> x.equals(cardNumber));
        if (found) {
            throw new BlacklistedPaymentCardNumberException("Payment card number is blacklisted. Please use another card for registration");
        }
    }

    private void checkDuplicateUsername(final String username) {
        final Optional<UserEntity> registrationEntity = Optional.ofNullable(registrationRepository.findByUsername(username));
        if (registrationEntity.isPresent()) {
            throw new DuplicateUsernameException("The Username you selected is already registered. Please enter a different Username");
        }
    }

    private void checkUserAge(final LocalDate dob) {
        if (RegistrationService.calculateAge(dob, LocalDate.now()) < ageLimit) {
            throw new InvalidAgeException("Age of user must be 18 years or above");
        }
    }

}