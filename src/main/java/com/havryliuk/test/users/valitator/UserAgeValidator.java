package com.havryliuk.test.users.valitator;

import com.havryliuk.test.users.exception.UserRegistrationRestrictionExceptions;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

import static com.havryliuk.test.users.util.GlobalConstants.USER_AGE_ERROR_MESSAGE;
import static com.havryliuk.test.users.util.GlobalConstants.USER_FIELD;

public class UserAgeValidator implements ConstraintValidator<ValidAge, LocalDate> {

    @Value("${constants.allowed-age-to-register}")
    private long ALLOWED_AGE_T0_REGISTER;

    @Override
    public void initialize(ValidAge constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
        if (birthDate == null) {
            return true;
        }
        boolean isRegistrationAllowed = LocalDate.now()
                .minusYears(ALLOWED_AGE_T0_REGISTER)
                .plusDays(1L)
                .isAfter(birthDate);
        if (!isRegistrationAllowed) {
            String cause = String.format(USER_AGE_ERROR_MESSAGE, ALLOWED_AGE_T0_REGISTER);
            throw new UserRegistrationRestrictionExceptions(USER_FIELD, cause);
        }
        return true;
    }

}