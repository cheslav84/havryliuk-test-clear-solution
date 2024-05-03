package com.havryliuk.test.users.util;

import com.havryliuk.test.users.dto.request.UserCreationDto;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.havryliuk.test.users.valitator.ValidatorConstants.LONG_PROPERTY_32;
import static com.havryliuk.test.users.valitator.ValidatorConstants.NOT_VALID_EMAIL;
import static com.havryliuk.test.users.valitator.ValidatorConstants.PROPERTY_REQUIRED;
import static com.havryliuk.test.users.valitator.ValidatorConstants.USER_AGE_ERROR_MESSAGE;

public class DataProvider {
    public static final Long ALLOWED_AGE_T0_REGISTER = 18L;

    private static Stream<Arguments> provideValidUserCreationDtos() {
        UserCreationDto withAllData = DtoCreator.createValidUserCreationDtoWithAllData();
        UserCreationDto withoutPhone = DtoCreator.createValidUserCreationDtoWithoutPhone();
        UserCreationDto withoutAddress = DtoCreator.createValidUserCreationDtoWithoutAddress();
        UserCreationDto withAddressWithoutCountry = DtoCreator.createValidUserCreationDtoAddressWithoutCountry();
        UserCreationDto withOnlyRequiredFields = DtoCreator.createValidUserCreationDtoWithOnlyRequiredFields();
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
        UserCreationDto emptyData = DtoCreator.createInvalidUserCreationDtoWithEmptyData();
        String[] dataProperties = {"data"};
        String[] requiredCause = {PROPERTY_REQUIRED};

        UserCreationDto withoutAllRequiredFields = DtoCreator.createInvalidUserCreationDtoWithoutAllRequiredFields();
        String[] allRequiredProperties = {"firstName", "email", "birthDate", "lastName"};
        String[] requiredFourCauses = getCauses(fourErrors, PROPERTY_REQUIRED);

        UserCreationDto withoutBirthDateAndWrongEmail = DtoCreator.createInvalidUserCreationDtoWithoutBirthDateAndWrongEmail();
        String[] birthDateAndEmailProperties = {"email", "birthDate"};
        String[] birthDateAndEmailCauses = {NOT_VALID_EMAIL, PROPERTY_REQUIRED};

        UserCreationDto longName = DtoCreator.createInvalidUserCreationDtoWithLongName();
        String[] nameProperties = {"firstName"};
        String[] nameCause = {LONG_PROPERTY_32};

        UserCreationDto wrongAge = DtoCreator.createInvalidUserCreationDtoWithWrongAge();
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
