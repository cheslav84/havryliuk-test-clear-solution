package com.havryliuk.test.users.util;

import com.havryliuk.test.users.dto.request.UserCreationDto;
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

    public static UserCreationDto createValidUserCreationDtoWithAllData() {
        Address address = createValidFullAddress();
        return UserCreationDto.builder()
                .data(createValidUserDto(address, PHONE_NUMBER))
                .build();
    }

    public static UserCreationDto createValidUserCreationDtoWithoutPhone() {
        Address address = createValidFullAddress();
        String phoneNumber = null;
        return UserCreationDto.builder()
                .data(createValidUserDto(address, phoneNumber))
                .build();
    }

    public static UserCreationDto createValidUserCreationDtoWithoutAddress() {
        Address address = null;
        return UserCreationDto.builder()
                .data(createValidUserDto(address, PHONE_NUMBER))
                .build();
    }

    public static UserCreationDto createValidUserCreationDtoAddressWithoutCountry() {
        Address address = createValidAddressWithoutCountry();
        return UserCreationDto.builder()
                .data(createValidUserDto(address, PHONE_NUMBER))
                .build();
    }

    public static UserCreationDto createValidUserCreationDtoWithOnlyRequiredFields() {
        Address address = createValidAddressWithoutCountry();
        String phoneNumber = null;
        return UserCreationDto.builder()
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



}
