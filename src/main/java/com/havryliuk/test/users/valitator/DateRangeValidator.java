package com.havryliuk.test.users.valitator;

import com.havryliuk.test.users.dto.request.BirthdayRangeDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static com.havryliuk.test.users.util.GlobalConstants.BIRTH_DATE_FROM_FIELD;
import static com.havryliuk.test.users.util.GlobalConstants.BIRTH_DATE_TO_FIELD;
import static com.havryliuk.test.users.util.GlobalConstants.NOT_COHERENT_DATES;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, BirthdayRangeDto> {

    @Override
    public void initialize(ValidDateRange constraintAnnotation) {
    }

    @Override
    public boolean isValid(BirthdayRangeDto birthDate, ConstraintValidatorContext context) {
        if (birthDate == null) {
            return true;
        }
        boolean isValid =  birthDate.birthDateFrom().isBefore(birthDate.birthDateTo());
        if (!isValid) {
            context
                    .buildConstraintViolationWithTemplate(NOT_COHERENT_DATES)
                    .addPropertyNode(BIRTH_DATE_FROM_FIELD)
                    .addConstraintViolation()
                    .buildConstraintViolationWithTemplate(NOT_COHERENT_DATES)
                    .addPropertyNode(BIRTH_DATE_TO_FIELD)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
        return isValid;
    }
}