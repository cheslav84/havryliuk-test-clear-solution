package com.havryliuk.test.users.util;

import com.havryliuk.test.users.dto.request.DataUserDto;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.havryliuk.test.users.util.GlobalConstants.LONG_PROPERTY_32;
import static com.havryliuk.test.users.util.GlobalConstants.NOT_VALID_EMAIL;
import static com.havryliuk.test.users.util.GlobalConstants.PROPERTY_REQUIRED;
import static com.havryliuk.test.users.util.GlobalConstants.USER_AGE_ERROR_MESSAGE;

public class DataUserCreationProvider {
    public static final Long ALLOWED_AGE_T0_REGISTER = 18L;

    private static Stream<Arguments> provideValidUserCreationDtos() {
        DataUserDto withAllData = DtoCreator.createValidUserCreationDtoWithAllData();
        DataUserDto withoutPhone = DtoCreator.createValidUserCreationDtoWithoutPhone();
        DataUserDto withoutAddress = DtoCreator.createValidUserCreationDtoWithoutAddress();
        DataUserDto withAddressWithoutCountry = DtoCreator.createValidUserCreationDtoAddressWithoutCountry();
        DataUserDto withOnlyRequiredFields = DtoCreator.createValidUserCreationDtoWithOnlyRequiredFields();
        return Stream.of(
                Arguments.of(withAllData),
                Arguments.of(withoutPhone),
                Arguments.of(withoutAddress),
                Arguments.of(withAddressWithoutCountry),
                Arguments.of(withOnlyRequiredFields)
        );
    }

    public static Stream<Arguments> provideInvalidUserCreationDtos() {
        String[] emptyMessages = {};
        int badRequest = 400;
        int forbidden = 403;
        int oneError = 1;
        int twoErrors = 2;
        int fourErrors = 4;
        DataUserDto emptyData = DtoCreator.createInvalidUserCreationDtoWithEmptyData();
        String[] dataProperties = {"data"};
        String[] requiredCause = {PROPERTY_REQUIRED};

        DataUserDto withoutAllRequiredFields = DtoCreator.createInvalidUserCreationDtoWithoutAllRequiredFields();
        String[] allRequiredProperties = {"firstName", "email", "birthDate", "lastName"};
        String[] requiredFourCauses = getCauses(fourErrors, PROPERTY_REQUIRED);

        DataUserDto withoutBirthDateAndWrongEmail = DtoCreator.createInvalidUserCreationDtoWithoutBirthDateAndWrongEmail();
        String[] birthDateAndEmailProperties = {"email", "birthDate"};
        String[] birthDateAndEmailCauses = {NOT_VALID_EMAIL, PROPERTY_REQUIRED};

        DataUserDto longName = DtoCreator.createInvalidUserCreationDtoWithLongName();
        String[] nameProperties = {"firstName"};
        String[] nameCause = {LONG_PROPERTY_32};

        DataUserDto wrongAge = DtoCreator.createInvalidUserCreationDtoWithWrongAge();
        String[] birthdateProperties = {"user"};
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
    private static String[] getCauses(int size, String cause) {
        String[] requiredFourCauses = new String[size];
        Arrays.fill(requiredFourCauses, cause);
        return requiredFourCauses;
    }
}
