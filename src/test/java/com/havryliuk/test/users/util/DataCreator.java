package com.havryliuk.test.users.util;

import com.havryliuk.test.users.model.Address;
import com.havryliuk.test.users.model.User;

import java.time.LocalDate;

import static com.havryliuk.test.users.util.GlobalConstants.BIRTH_DATE;
import static com.havryliuk.test.users.util.GlobalConstants.CITY;
import static com.havryliuk.test.users.util.GlobalConstants.COUNTRY;
import static com.havryliuk.test.users.util.GlobalConstants.EMAIL;
import static com.havryliuk.test.users.util.GlobalConstants.FIRST_NAME;
import static com.havryliuk.test.users.util.GlobalConstants.LAST_NAME;
import static com.havryliuk.test.users.util.GlobalConstants.PHONE_NUMBER;
import static com.havryliuk.test.users.util.GlobalConstants.STREET;
import static com.havryliuk.test.users.util.GlobalConstants.TESTING_UUID;
import static com.havryliuk.test.users.util.GlobalConstants.ZEEP_CODE;

public class DataCreator {

    public static User createUser() {
        return User.builder()
                .id(TESTING_UUID)
                .email(EMAIL)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .birthDate(LocalDate.parse(BIRTH_DATE))
                .phoneNumber(PHONE_NUMBER)
                .address(createValidFullAddress())
                .build();
    }

    public static Address createValidFullAddress() {
        return Address.builder()
                .country(COUNTRY)
                .city(CITY)
                .street(STREET)
                .zipcode(ZEEP_CODE)
                .build();
    }

    public static Address createValidAddressWithoutCountry() {
        return Address.builder()
                .city(CITY)
                .street(STREET)
                .zipcode(ZEEP_CODE)
                .build();
    }
}
