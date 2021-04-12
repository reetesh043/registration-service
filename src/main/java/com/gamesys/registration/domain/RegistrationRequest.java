package com.gamesys.registration.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import static com.gamesys.registration.RegistrationServiceConstants.USER_NAME_REGEX;
import static com.gamesys.registration.RegistrationServiceConstants.USER_NAME_VALIDATION_MESSAGE;
import static com.gamesys.registration.RegistrationServiceConstants.PASSWORD_REGEX;
import static com.gamesys.registration.RegistrationServiceConstants.PASSWORD_VALIDATION_MESSAGE;
import static com.gamesys.registration.RegistrationServiceConstants.PAN_CARD_REGEX;
import static com.gamesys.registration.RegistrationServiceConstants.PAN_VALIDATION_MESSAGE;
import static com.gamesys.registration.RegistrationServiceConstants.MSG_INVALID_FIELD_VALUE;
import static com.gamesys.registration.RegistrationServiceConstants.USERNAME;
import static com.gamesys.registration.RegistrationServiceConstants.PASSWORD;
import static com.gamesys.registration.RegistrationServiceConstants.DOB;
import static com.gamesys.registration.RegistrationServiceConstants.PAYMENT_CARD_NUMBER;

/**
 * User registration request object.
 */

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public final class RegistrationRequest {

    @Valid
    @NotNull(message = USERNAME + MSG_INVALID_FIELD_VALUE)
    @Pattern(regexp = USER_NAME_REGEX, message = USER_NAME_VALIDATION_MESSAGE)
    private final String username;

    @Valid
    @NotNull(message = PASSWORD + MSG_INVALID_FIELD_VALUE)
    @Pattern(regexp = PASSWORD_REGEX, message = PASSWORD_VALIDATION_MESSAGE)
    private final String password;

    @Valid
    @NotNull(message = DOB + MSG_INVALID_FIELD_VALUE)
    private LocalDate dob;

    @Valid
    @NotNull(message = PAYMENT_CARD_NUMBER + MSG_INVALID_FIELD_VALUE)
    @Pattern(regexp = PAN_CARD_REGEX, message = PAN_VALIDATION_MESSAGE)
    private final String paymentCardNumber;
}
