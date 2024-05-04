package com.havryliuk.test.users.util;

import com.havryliuk.test.users.dto.request.DataUserDto;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.havryliuk.test.users.util.GlobalConstants.BIRTH_DATE_FIELD;
import static com.havryliuk.test.users.util.GlobalConstants.DATA_FIELD;
import static com.havryliuk.test.users.util.GlobalConstants.EMAIL_FIELD;
import static com.havryliuk.test.users.util.GlobalConstants.FIRST_NAME_FIELD;
import static com.havryliuk.test.users.util.GlobalConstants.LAST_NAME_FIELD;
import static com.havryliuk.test.users.util.GlobalConstants.LONG_PROPERTY_32;
import static com.havryliuk.test.users.util.GlobalConstants.NOT_VALID_EMAIL;
import static com.havryliuk.test.users.util.GlobalConstants.PROPERTY_REQUIRED;
import static com.havryliuk.test.users.util.GlobalConstants.USER_AGE_ERROR_MESSAGE;
import static com.havryliuk.test.users.util.GlobalConstants.USER_FIELD;

public class DataUserRequiredFieldsProvider {
    public static final Long ALLOWED_AGE_T0_REGISTER = 18L;

    @SuppressWarnings("unused")
    private static Stream<Arguments> provideValidUserDtos() {
        DataUserDto withAllData = DtoCreator.createValidUserDtoWithAllData();
        DataUserDto withoutPhone = DtoCreator.createValidUserDtoWithoutPhone();
        DataUserDto withoutAddress = DtoCreator.createValidUserDtoWithoutAddress();
        DataUserDto withAddressWithoutCountry = DtoCreator.createValidUserDtoAddressWithoutCountry();
        DataUserDto withOnlyRequiredFields = DtoCreator.createValidUserDtoWithOnlyRequiredFields();
        return Stream.of(
                Arguments.of(withAllData),
                Arguments.of(withoutPhone),
                Arguments.of(withoutAddress),
                Arguments.of(withAddressWithoutCountry),
                Arguments.of(withOnlyRequiredFields)
        );
    }

    @SuppressWarnings("unused")
    public static Stream<Arguments> provideInvalidUserDtos() {
        String[] emptyMessages = {};
        int badRequest = 400;
        int forbidden = 403;
        int oneError = 1;
        int twoErrors = 2;
        int fourErrors = 4;
        DataUserDto emptyData = DtoCreator.createUserDtoWithEmptyData();
        String[] dataProperties = {DATA_FIELD};
        String[] requiredCause = {PROPERTY_REQUIRED};

        DataUserDto withoutAllRequiredFields = DtoCreator.createUserDtoWithoutAllRequiredFields();
        String[] allRequiredProperties = {FIRST_NAME_FIELD, EMAIL_FIELD, BIRTH_DATE_FIELD, LAST_NAME_FIELD};
        String[] requiredFourCauses = getCauses(fourErrors);

        DataUserDto withoutBirthDateAndWrongEmail = DtoCreator.createUserDtoWithoutBirthDateAndWrongEmail();
        String[] birthDateAndEmailProperties = {EMAIL_FIELD, BIRTH_DATE_FIELD};
        String[] birthDateAndEmailCauses = {NOT_VALID_EMAIL, PROPERTY_REQUIRED};

        DataUserDto longName = DtoCreator.createInvalidUserDtoWithLongName();
        String[] nameProperties = {FIRST_NAME_FIELD};
        String[] nameCause = {LONG_PROPERTY_32};

        DataUserDto wrongAge = DtoCreator.createInvalidUserDtoWithWrongAge();
        String[] birthdateProperties = {USER_FIELD};
        String[] birthdateCause = {String.format(USER_AGE_ERROR_MESSAGE, ALLOWED_AGE_T0_REGISTER)};

        return Stream.of(
                Arguments.of(emptyData, oneError, badRequest, dataProperties, requiredCause, emptyMessages),
                Arguments.of(withoutAllRequiredFields, fourErrors, badRequest, allRequiredProperties, requiredFourCauses, emptyMessages),
                Arguments.of(withoutBirthDateAndWrongEmail, twoErrors, badRequest, birthDateAndEmailProperties, birthDateAndEmailCauses, emptyMessages),
                Arguments.of(longName, oneError, badRequest, nameProperties, nameCause, emptyMessages),
                Arguments.of(wrongAge, oneError, forbidden, birthdateProperties, birthdateCause, emptyMessages)
        );
    }

    @NotNull
    private static String[] getCauses(int size) {
        String[] requiredFourCauses = new String[size];
        Arrays.fill(requiredFourCauses, GlobalConstants.PROPERTY_REQUIRED);
        return requiredFourCauses;
    }
}
