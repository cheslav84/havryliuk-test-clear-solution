package com.havryliuk.test.users.util;

import com.havryliuk.test.users.dto.UserDtoResponse;
import com.havryliuk.test.users.dto.request.BirthdayRangeDto;
import com.havryliuk.test.users.dto.request.DataUserDto;
import com.havryliuk.test.users.dto.UserDto;
import com.havryliuk.test.users.dto.response.UserShortDtoResponse;
import com.havryliuk.test.users.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.havryliuk.test.users.util.GlobalConstants.BIRTH_DATE;
import static com.havryliuk.test.users.util.GlobalConstants.EMAIL;
import static com.havryliuk.test.users.util.GlobalConstants.FIRST_NAME;
import static com.havryliuk.test.users.util.GlobalConstants.LAST_NAME;
import static com.havryliuk.test.users.util.GlobalConstants.PHONE_NUMBER;

public class DtoCreator {

    public static DataUserDto createValidUserDtoWithAllData() {
        Address address = DataCreator.createValidFullAddress();
        return DataUserDto.builder()
                .data(createValidUserDto(address, PHONE_NUMBER))
                .build();
    }

    public static DataUserDto createValidUserDtoWithoutPhone() {
        Address address = DataCreator.createValidFullAddress();
        return DataUserDto.builder()
                .data(createValidUserDto(address, null))
                .build();
    }

    public static DataUserDto createValidUserDtoWithoutAddress() {
        return DataUserDto.builder()
                .data(createValidUserDto(null, PHONE_NUMBER))
                .build();
    }

    public static DataUserDto createValidUserDtoAddressWithoutCountry() {
        Address address = DataCreator.createValidAddressWithoutCountry();
        return DataUserDto.builder()
                .data(createValidUserDto(address, PHONE_NUMBER))
                .build();
    }

    public static DataUserDto createValidUserDtoWithOnlyRequiredFields() {
        Address address = DataCreator.createValidAddressWithoutCountry();
        return DataUserDto.builder()
                .data(createValidUserDto(address, null))
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

    public static DataUserDto createUserDtoWithEmptyData() {
        return DataUserDto.builder()
                .build();
    }

    public static DataUserDto createUserDtoWithoutAllRequiredFields() {
        return DataUserDto.builder()
                .data(UserDto.builder().build())
                .build();
    }

    public static DataUserDto createUserDtoWithoutBirthDateAndWrongEmail() {
        return DataUserDto.builder()
                .data(UserDto.builder()
                        .email("wrong.email")
                        .firstName("Name")
                        .lastName("Surname")
                        .build())
                .build();
    }

    public static DataUserDto createInvalidUserDtoWithWrongAge() {
        return DataUserDto.builder()
                .data(UserDto.builder()
                        .email("domain@email.com")
                        .firstName("Name")
                        .lastName("Surname")
                        .birthDate(LocalDate.parse("2020-05-02"))//todo refactor hardCodding
                        .build())
                .build();
    }

    public static DataUserDto createInvalidUserDtoWithLongName() {
        return DataUserDto.builder()
                .data(UserDto.builder()
                        .email("domain@email.com")
                        .firstName("VeeeeeeeeeeeeeeerryyyyylooooongNaaaaaameeeeeeeeeeeeeee")
                        .lastName("Surname")
                        .birthDate(LocalDate.parse("1980-01-01"))//todo refactor hardCodding
                        .build())
                .build();
    }

    public static BirthdayRangeDto createBirthdayRangeDto() {
        return BirthdayRangeDto.builder()
                .birthDateFrom(LocalDate.of(1996, 4, 5))
                .birthDateTo(LocalDate.of(2008, 4, 5))
                .build();
    }


    public static Page<UserShortDtoResponse> createPageOfUsers() {
        List<UserShortDtoResponse> list = createUserDtoResponseList();
        return new PageImpl<>(list);
    }

    private static List<UserShortDtoResponse> createUserDtoResponseList() {
        LocalDate firstDate = LocalDate.of(1999, 4, 5);
        LocalDate secondDate = LocalDate.of(2002, 5, 7);
        return List.of(
                createPageOfUsers(UUID.randomUUID().toString(), 1, firstDate),
                createPageOfUsers(UUID.randomUUID().toString(), 2, secondDate)
        );
    }

    public static UserShortDtoResponse createPageOfUsers(String id, int number, LocalDate date) {
        return UserShortDtoResponse.builder()
                .id(id)
                .email(EMAIL)
                .firstName(FIRST_NAME + number)
                .lastName(LAST_NAME + number)
                .birthDate(date)
                .build();
    }

    public static UserDtoResponse createUserDto(String id) {
        return UserDtoResponse.builder()
                .id(id)
                .email(EMAIL)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .birthDate(LocalDate.parse(BIRTH_DATE))
                .phoneNumber(PHONE_NUMBER)
                .address(DataCreator.createValidFullAddress())
                .build();
    }

    public static DataUserDto createUserDtoWithLastNameAndAddressCounty(String lastName, String country) {
        Address address = Address.builder().country(country).build();
        return DataUserDto.builder()
                .data(UserDto.builder()
                        .lastName(lastName)
                        .address(address)
                        .build()
                )
                .build();
    }
}
