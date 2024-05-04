//package com.havryliuk.test.users.valitator;
//
//import com.havryliuk.test.users.valitator.groups.UserCreating;
//import com.havryliuk.test.users.valitator.groups.UserUpdating;
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//import jakarta.validation.constraints.NotNull;
//import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
//
//import java.util.regex.Pattern;
//
//import static com.havryliuk.test.users.valitator.ValidatorConstants.PROPERTY_REQUIRED;
//import static com.havryliuk.test.users.valitator.ValidatorConstants.LONG_PROPERTY_32;
//import static com.havryliuk.test.users.valitator.ValidatorConstants.NOT_VALID_EMAIL;
//import static com.havryliuk.test.users.valitator.ValidatorConstants.EMAIL_PATTERN;
//
//public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
//
//    private boolean nullable = false;
//
//    @Override
//    public void initialize(ValidEmail constraintAnnotation) {
////        Class<?>[] groups = constraintAnnotation.groups();
////        for (Class<?> group : groups) {
////            if (group == UserUpdating.class) {
////                nullable = true;
////                break;
////            }
////        }
//    }
//
//    @Override
//    public boolean isValid(@NotNull String email, ConstraintValidatorContext context) {
//        boolean isValid = true;
//
////        Object dynamicPayload = context.getConstraintDescriptor().getAttributes().get("jakarta.validation.groups");
////        Class<?>[] activeGroups = (Class<?>[]) context.getConstraintDescriptor().getAttributes().get("jakarta.validation.groups");
//
//
////        Class<?>[] activeGroups = (Class<?>[]) context.getConstraintDescriptor().getAttributes().get("jakarta.validation.groups");
//
//
//        if (email == null) {
//            context.buildConstraintViolationWithTemplate(PROPERTY_REQUIRED).addConstraintViolation();
//            return nullable;
//        }
//        if (email.length() > 32) {
//            context.buildConstraintViolationWithTemplate(LONG_PROPERTY_32).addConstraintViolation();
//            isValid = false;
//        }
//        if (!Pattern.compile(EMAIL_PATTERN).matcher(email).matches()) {
//            context.buildConstraintViolationWithTemplate(NOT_VALID_EMAIL).addConstraintViolation();
//            isValid = false;
//        }
//
//        if (!isValid) {
//            context.disableDefaultConstraintViolation();
//        }
//        return isValid;
//    }
//
//
//
//}
