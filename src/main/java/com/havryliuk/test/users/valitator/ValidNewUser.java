//package com.havryliuk.test.users.valitator;
//
//import jakarta.validation.Constraint;
//import jakarta.validation.Payload;
//
//import java.lang.annotation.Documented;
//import java.lang.annotation.Retention;
//import java.lang.annotation.Target;
//
//import static com.havryliuk.test.users.swager.Constants.USER_REQUIRED;
//import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
//import static java.lang.annotation.ElementType.FIELD;
//import static java.lang.annotation.ElementType.TYPE;
//import static java.lang.annotation.RetentionPolicy.RUNTIME;
//
//@Target({TYPE, FIELD, ANNOTATION_TYPE})
//@Retention(RUNTIME)
//@Constraint(validatedBy = NewUserCreationValidator.class)
//@Documented
//public @interface ValidNewUser {
//    String message() default USER_REQUIRED;
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
//}
