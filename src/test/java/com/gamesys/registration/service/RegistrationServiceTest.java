package com.gamesys.registration.service;

import com.gamesys.registration.domain.RegistrationRequest;
import com.gamesys.registration.domain.UserEntity;
import com.gamesys.registration.exceptions.BlacklistedPaymentCardNumberException;
import com.gamesys.registration.exceptions.DuplicateUsernameException;
import com.gamesys.registration.exceptions.InvalidAgeException;
import com.gamesys.registration.repository.RegistrationRepository;
import com.gamesys.registration.validators.RegistrationConstraintValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
@DisplayName("Registration Service Test")
class RegistrationServiceTest {

    @InjectMocks
    private RegistrationServiceImpl classUnderTest;

    @Mock
    private RegistrationRepository registrationRepository;

    @Mock
    private RegistrationConstraintValidator validator;

    @BeforeEach
    private void setUp(){

    }

    @Test
    @DisplayName("Register user successfully")
    public void register_user_successfully(){
        RegistrationRequest request = new RegistrationRequest("Reetesh12","ABCD12", LocalDate.of(1995, 04, 01), "3212345678987654");
        doNothing().when(validator).validateUserDetails(any(RegistrationRequest.class));
        classUnderTest.register(request);
        verify(validator, times(1)).validateUserDetails(any(RegistrationRequest.class));
        verify(registrationRepository, times(1)).save(any(UserEntity.class));

    }

    @Test
    @DisplayName("Throw exception when age is below 18")
    public void throw_exception_when_age_is_less_than_18() throws InvalidAgeException{
        RegistrationRequest request = new RegistrationRequest("Reetesh12","ABCD12", LocalDate.of(2005, 04, 01), "3212345678987654");
        doThrow(new InvalidAgeException("Age of user must be 18 years or above")).when(validator).validateUserDetails(any(RegistrationRequest.class));
        Exception exception = Assertions.assertThrows(InvalidAgeException.class, () -> {
            classUnderTest.register(request);
        });
        String expectedMessage = "Age of user must be 18 years or above";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(validator, times(1)).validateUserDetails(any(RegistrationRequest.class));
        verify(registrationRepository, times(0)).save(any(UserEntity.class));

    }

    @Test
    @DisplayName("Throw exception when username is already registered")
    public void throw_exception_when_username_already_registered() throws InvalidAgeException{
        RegistrationRequest request = new RegistrationRequest("Reetesh12","ABCD12", LocalDate.of(1995, 04, 01), "3212345678987654");
        doThrow(new DuplicateUsernameException("The Username you selected is already registered. Please enter a different Username")).when(validator).validateUserDetails(any(RegistrationRequest.class));
        Exception exception = Assertions.assertThrows(DuplicateUsernameException.class, () -> {
            classUnderTest.register(request);
        });
        String expectedMessage = "The Username you selected is already registered. Please enter a different Username";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(validator, times(1)).validateUserDetails(any(RegistrationRequest.class));
        verify(registrationRepository, times(0)).save(any(UserEntity.class));
    }

    @Test
    @DisplayName("Throw exception when payment card number is blacklisted")
    public void throw_exception_when_payment_card_number_is_blacklisted() throws InvalidAgeException{
        RegistrationRequest request = new RegistrationRequest("Reetesh12","ABCD12", LocalDate.of(1995, 04, 01), "3212345678987654");
        doThrow(new BlacklistedPaymentCardNumberException("Payment card number is blacklisted. Please use another card for registration")).when(validator).validateUserDetails(any(RegistrationRequest.class));
        Exception exception = Assertions.assertThrows(BlacklistedPaymentCardNumberException.class, () -> {
            classUnderTest.register(request);
        });
        String expectedMessage = "Payment card number is blacklisted. Please use another card for registration";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(validator, times(1)).validateUserDetails(any(RegistrationRequest.class));
        verify(registrationRepository, times(0)).save(any(UserEntity.class));
    }

}

