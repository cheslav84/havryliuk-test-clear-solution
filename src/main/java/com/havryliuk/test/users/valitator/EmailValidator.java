package com.havryliuk.test.users.valitator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

import static com.havryliuk.test.users.valitator.ValidatorConstants.PROPERTY_REQUIRED;
import static com.havryliuk.test.users.valitator.ValidatorConstants.LONG_PROPERTY_32;
import static com.havryliuk.test.users.valitator.ValidatorConstants.NOT_VALID_EMAIL;
import static com.havryliuk.test.users.valitator.ValidatorConstants.EMAIL_PATTERN;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        boolean isValid = true;

        if (email == null) {
            context.buildConstraintViolationWithTemplate(PROPERTY_REQUIRED).addConstraintViolation();
            return false;
        }
        if (email.length() > 32) {
            context.buildConstraintViolationWithTemplate(LONG_PROPERTY_32).addConstraintViolation();
            isValid = false;
        }
        if (!Pattern.compile(EMAIL_PATTERN).matcher(email).matches()) {
            context.buildConstraintViolationWithTemplate(NOT_VALID_EMAIL).addConstraintViolation();
            isValid = false;
        }

        if (!isValid) {
            context.disableDefaultConstraintViolation();
        }
        return isValid;
    }
}
