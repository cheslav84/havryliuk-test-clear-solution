package com.havryliuk.test.users.util.mapper;

import com.havryliuk.test.users.dto.UserDto;
import com.havryliuk.test.users.model.User;
import java.util.UUID;

public class UserMapper {

    public static User toUser(UserDto dto) {
        return User.builder()
                .id(UUID.randomUUID().toString())
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .birthDate(dto.getBirthDate())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .build();
    }
}
