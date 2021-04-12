package com.gamesys.registration;

/**
 * Constant class.
 */
public class RegistrationServiceConstants {

    private RegistrationServiceConstants(){
        // default constructor
    }

    // alphanumerical, no spaces
    public static final String USER_NAME_REGEX = "^[a-zA-Z0-9]+$";
    public static final String USER_NAME_VALIDATION_MESSAGE = "The username must be alphanumerical, no spaces";

    // at least four characters, at least one upper case character, at least one number
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{4,}$";
    public static final String PASSWORD_VALIDATION_MESSAGE =
            "The password must be of min length 4, at least one upper case letter and number";
    public static final String PAN_CARD_REGEX = "^[0-9]{15,19}$";
    public static final String PAN_VALIDATION_MESSAGE = "Payment card number must be between 15 and 19 digits";

    public static final String USERNAME = "username ";
    public static final String PASSWORD = "password ";
    public static final String DOB = "date of birth ";
    public static final String PAYMENT_CARD_NUMBER = "payment card number ";
    public static final String BLACKLISTED_IIN_CACHE = "blacklisted_iin";
    public static final String BLACKLISTED_IIN_CACHE_KEY = "blacklistedPaymentIssuerIdentificationNumber";
    public static final String MSG_INVALID_FIELD_VALUE = "must not be null ";

}
