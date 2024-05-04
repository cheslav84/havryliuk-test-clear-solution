package com.havryliuk.test.users.util;

import com.havryliuk.test.users.dto.request.DataUserDto;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static com.havryliuk.test.users.util.GlobalConstants.LONG_PROPERTY_32;
import static com.havryliuk.test.users.util.GlobalConstants.NOT_VALID_EMAIL;
import static com.havryliuk.test.users.util.GlobalConstants.PROPERTY_REQUIRED;
import static com.havryliuk.test.users.util.GlobalConstants.USER_AGE_ERROR_MESSAGE;

@SuppressWarnings("unused")
public class DataUserOptionalFieldsProvider {
    public static final Long ALLOWED_AGE_T0_REGISTER = 18L;

    @SuppressWarnings("unused")
    private static Stream<Arguments> provideValidUserDtos() {
        DataUserDto withEmptyData = DtoCreator.createUserDtoWithEmptyData();
        DataUserDto withoutAnyField = DtoCreator.createUserDtoWithoutAllRequiredFields();

        return Stream.of(
                Arguments.of(withEmptyData),
                Arguments.of(withoutAnyField)
        );
    }

    @SuppressWarnings("unused")
    public static Stream<Arguments> provideInvalidUserDtos() {
        String[] emptyMessages = {};
        int badRequest = 400;
        int forbidden = 403;
        int oneError = 1;
        int twoErrors = 2;

        DataUserDto withoutBirthDateAndWrongEmail = DtoCreator.createUserDtoWithoutBirthDateAndWrongEmail();
        String[] birthDateAndEmailProperties = {"email", "birthDate"};
        String[] birthDateAndEmailCauses = {NOT_VALID_EMAIL, PROPERTY_REQUIRED};

        DataUserDto longName = DtoCreator.createInvalidUserDtoWithLongName();
        String[] nameProperties = {"firstName"};
        String[] nameCause = {LONG_PROPERTY_32};

        DataUserDto wrongAge = DtoCreator.createInvalidUserDtoWithWrongAge();
        String[] birthdateProperties = {"user"};
        String[] birthdateCause = {String.format(USER_AGE_ERROR_MESSAGE, ALLOWED_AGE_T0_REGISTER)};

        return Stream.of(
                Arguments.of(withoutBirthDateAndWrongEmail, twoErrors, badRequest, birthDateAndEmailProperties, birthDateAndEmailCauses, emptyMessages),
                Arguments.of(longName, oneError, badRequest, nameProperties, nameCause, emptyMessages),
                Arguments.of(wrongAge, oneError, forbidden, birthdateProperties, birthdateCause, emptyMessages)
        );
    }

}
