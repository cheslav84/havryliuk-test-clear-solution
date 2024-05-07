package com.havryliuk.test.users.util.mapper;

import com.havryliuk.test.users.dto.UserDto;
import com.havryliuk.test.users.model.Address;
import com.havryliuk.test.users.model.User;

import java.util.function.Consumer;

public class UserMapper {

    public static User toUser(String id, UserDto dto) {
        return User.builder()
                .id(id)
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .birthDate(dto.getBirthDate())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .build();
    }

    public static void updateUser(User user, UserDto data, boolean updateNullableFields) {
        if (data == null) {
            return;
        }
        update(user::setEmail, data.getEmail(), updateNullableFields);
        update(user::setFirstName, data.getFirstName(), updateNullableFields);
        update(user::setLastName, data.getLastName(), updateNullableFields);
        update(user::setPhoneNumber, data.getPhoneNumber(), updateNullableFields);
        updateAddress(user, data.getAddress(), updateNullableFields);
    }

    private static void updateAddress(User user, Address data, boolean updateNullableFields) {
        if (updateNullableFields) {
            user.setAddress(data);
        } else {
            if (data == null) {
                return;
            }
            updateAddressFields(user, data);
        }
    }

    private static void updateAddressFields(User user, Address data) {
        Address address = user.getAddress();
        if (address == null) {
            address = new Address();
            user.setAddress(address);
        }
        update(address::setCountry, data.getCountry());
        update(address::setCity, data.getCity());
        update(address::setStreet, data.getStreet());
        update(address::setZipcode, data.getZipcode());
    }

    private static <T> void update(Consumer<T> consumer, T value, boolean updateNullableFields) {
        if (value != null || updateNullableFields) {
            consumer.accept(value);
        }
    }

    private static <T> void update(Consumer<T> consumer, T value) {
        if (value != null) {
            consumer.accept(value);
        }
    }
}
