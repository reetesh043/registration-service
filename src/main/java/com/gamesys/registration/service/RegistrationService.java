package com.gamesys.registration.service;

import com.gamesys.registration.domain.RegistrationRequest;
import java.time.LocalDate;
import java.time.Period;


public interface RegistrationService {


    /** Method to register user successfully
     *
     * @param request
     */
    void register(final RegistrationRequest request);


    /** Static utility method: Calculate age based on given current date and birth date
     *
     * @param birthDate
     * @param currentDate
     * @return
     */

     static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

}
