package com.havryliuk.test.users.valitator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

import static com.havryliuk.test.users.valitator.ValidatorConstants.EMAIL_REQUIRED;
import static com.havryliuk.test.users.valitator.ValidatorConstants.LONG_EMAIL;
import static com.havryliuk.test.users.valitator.ValidatorConstants.NOT_VALID_EMAIL;
import static com.havryliuk.test.users.valitator.ValidatorConstants.EMAIL_REGEX;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        boolean isValid = true;

        if (email == null) {
            context.buildConstraintViolationWithTemplate(EMAIL_REQUIRED).addConstraintViolation();
            return false;
        }
        if (email.length() > 32) {
            context.buildConstraintViolationWithTemplate(LONG_EMAIL).addConstraintViolation();
            isValid = false;
        }
        if (!Pattern.compile(EMAIL_REGEX).matcher(email).matches()) {
            context.buildConstraintViolationWithTemplate(NOT_VALID_EMAIL).addConstraintViolation();
            isValid = false;
        }

        if (!isValid) {
            context.disableDefaultConstraintViolation();
        }
        return isValid;
    }
}
