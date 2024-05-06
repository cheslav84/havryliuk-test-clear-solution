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

    public static void patchUser(User user, UserDto data) {
        if (data == null) {
            return;
        }
        updateIfPresent(user::setEmail, data.getEmail());
        updateIfPresent(user::setFirstName, data.getFirstName());
        updateIfPresent(user::setLastName, data.getLastName());
        updateIfPresent(user::setPhoneNumber, data.getPhoneNumber());
        patchAddress(user.getAddress(), data.getAddress());
    }

    private static void patchAddress(Address address, Address data) {
        updateIfPresent(address::setCountry, data.getCountry());
        updateIfPresent(address::setCity, data.getCity());
        updateIfPresent(address::setStreet, data.getStreet());
        updateIfPresent(address::setZipcode, data.getZipcode());

    }

    private static <T> void updateIfPresent(Consumer<T> consumer, T value){
        if (value != null){
            consumer.accept(value);
        }
    }
}
