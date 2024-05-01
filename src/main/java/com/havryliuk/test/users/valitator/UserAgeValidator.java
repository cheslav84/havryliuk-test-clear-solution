package com.havryliuk.test.users.valitator;

import com.havryliuk.test.users.exception.UserRegistrationRestrictionExceptions;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public class UserAgeValidator implements ConstraintValidator<ValidAge, LocalDate> {

    @Value("${constants.allowed-age-to-register}")
    private long ALLOWED_AGE_T0_REGISTER;

    @Override
    public void initialize(ValidAge constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
        if (birthDate == null) {
            return false;
        }

        boolean isRegistrationAllowed = LocalDate.now()
                .minusYears(ALLOWED_AGE_T0_REGISTER)
                .plusDays(1L)
                .isAfter(birthDate);
        if (!isRegistrationAllowed) {
            throw new UserRegistrationRestrictionExceptions(String.valueOf(ALLOWED_AGE_T0_REGISTER));
        }
        return true;
    }

}