package com.havryliuk.test.users.util;

import com.havryliuk.test.users.dto.request.DataUserDto;
import com.havryliuk.test.users.dto.request.UserDto;
import com.havryliuk.test.users.model.Address;

import java.time.LocalDate;

import static com.havryliuk.test.users.util.GlobalConstants.BIRTH_DATE;
import static com.havryliuk.test.users.util.GlobalConstants.CITY;
import static com.havryliuk.test.users.util.GlobalConstants.COUNTRY;
import static com.havryliuk.test.users.util.GlobalConstants.EMAIL;
import static com.havryliuk.test.users.util.GlobalConstants.FIRST_NAME;
import static com.havryliuk.test.users.util.GlobalConstants.LAST_NAME;
import static com.havryliuk.test.users.util.GlobalConstants.PHONE_NUMBER;
import static com.havryliuk.test.users.util.GlobalConstants.STREET;
import static com.havryliuk.test.users.util.GlobalConstants.ZEEP_CODE;

public class DtoCreator {

    public static DataUserDto createValidUserCreationDtoWithAllData() {
        Address address = createValidFullAddress();
        return DataUserDto.builder()
                .data(createValidUserDto(address, PHONE_NUMBER))
                .build();
    }

    public static DataUserDto createValidUserCreationDtoWithoutPhone() {
        Address address = createValidFullAddress();
        String phoneNumber = null;
        return DataUserDto.builder()
                .data(createValidUserDto(address, phoneNumber))
                .build();
    }

    public static DataUserDto createValidUserCreationDtoWithoutAddress() {
        Address address = null;
        return DataUserDto.builder()
                .data(createValidUserDto(address, PHONE_NUMBER))
                .build();
    }

    public static DataUserDto createValidUserCreationDtoAddressWithoutCountry() {
        Address address = createValidAddressWithoutCountry();
        return DataUserDto.builder()
                .data(createValidUserDto(address, PHONE_NUMBER))
                .build();
    }

    public static DataUserDto createValidUserCreationDtoWithOnlyRequiredFields() {
        Address address = createValidAddressWithoutCountry();
        String phoneNumber = null;
        return DataUserDto.builder()
                .data(createValidUserDto(address, phoneNumber))
                .build();
    }

    private static UserDto createValidUserDto(Address address, String phone) {
        return UserDto.builder()
                .email(EMAIL)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .birthDate(LocalDate.parse(BIRTH_DATE))
                .phoneNumber(phone)
                .address(address)
                .build();
    }

    private static Address createValidFullAddress() {
        return Address.builder()
                .country(COUNTRY)
                .city(CITY)
                .street(STREET)
                .zipcode(ZEEP_CODE)
                .build();
    }

    private static Address createValidAddressWithoutCountry() {
        return Address.builder()
                .city(CITY)
                .street(STREET)
                .zipcode(ZEEP_CODE)
                .build();
    }


    public static DataUserDto createInvalidUserCreationDtoWithEmptyData() {
        return DataUserDto.builder()
                .build();
    }

    public static DataUserDto createInvalidUserCreationDtoWithoutAllRequiredFields() {
        return DataUserDto.builder()
                .data(UserDto.builder().build())
                .build();
    }

    public static DataUserDto createInvalidUserCreationDtoWithoutBirthDateAndWrongEmail() {
        return DataUserDto.builder()
                .data(UserDto.builder()
                        .email("wrong.email")
                        .firstName("Name")
                        .lastName("Surname")
                        .build())
                .build();
    }

    public static DataUserDto createInvalidUserCreationDtoWithWrongAge() {
        return DataUserDto.builder()
                .data(UserDto.builder()
                        .email("domain@email.com")
                        .firstName("Name")
                        .lastName("Surname")
                        .birthDate(LocalDate.parse("2020-05-02"))
                        .build())
                .build();
    }

    public static DataUserDto createInvalidUserCreationDtoWithLongName() {
        return DataUserDto.builder()
                .data(UserDto.builder()
                        .email("domain@email.com")
                        .firstName("VeeeeeeeeeeeeeeerryyyyylooooongNaaaaaameeeeeeeeeeeeeee")
                        .lastName("Surname")
                        .birthDate(LocalDate.parse("1980-01-01"))
                        .build())
                .build();
    }
}
