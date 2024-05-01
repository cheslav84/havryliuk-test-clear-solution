//package com.havryliuk.test.users.valitator;
//
//import com.havryliuk.test.users.dto.request.UserCreationDto;
//import com.havryliuk.test.users.dto.request.UserDto;
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//import java.util.regex.Pattern;
//
//import static com.havryliuk.test.users.swager.Constants.EMAIL_REQUIRED;
//import static com.havryliuk.test.users.swager.Constants.LONG_EMAIL;
//import static com.havryliuk.test.users.swager.Constants.NOT_VALID_EMAIL;
//
//public class NewUserCreationValidator implements ConstraintValidator<ValidNewUser, UserDto> {
//    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
//
//
//    @Override
//    public void initialize(ValidNewUser constraintAnnotation) {
//    }
//
//    @Override
//    public boolean isValid(UserDto user, ConstraintValidatorContext context) {
//        if (user == null) {
//            return false;
//        }
//
//        boolean isValid = isEmailValid(user, context);
//
//
//        //        if (!isAllRequiredFieldPresent(user, messageConstructor)) {
////            isValid = false;
////        }
//
//
//        if (!isValid) {
//            context.disableDefaultConstraintViolation();
//        }
//        return isValid;
//    }
//
//    private boolean isEmailValid(UserDto user, ConstraintValidatorContext context) {
//        if (user.getEmail() == null) {
//            context.buildConstraintViolationWithTemplate(EMAIL_REQUIRED).addConstraintViolation();
//            return false;
//        }
//        if (user.getEmail().length() > 32) {
//            context.buildConstraintViolationWithTemplate(LONG_EMAIL).addConstraintViolation();
//            return false;
//        }
//        if (!Pattern.compile(EMAIL_REGEX).matcher(user.getEmail()).matches()) {
//            context.buildConstraintViolationWithTemplate(NOT_VALID_EMAIL).addConstraintViolation();
//            return false;
//        }
//        return true;
//    }
//
//
//}
