package com.havryliuk.test.users.dto;

import com.havryliuk.test.users.model.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

import static com.havryliuk.test.users.util.GlobalConstants.UUID_EXAMPLE;

@Getter
@SuperBuilder
@NoArgsConstructor
public final class UserDtoResponse extends UserDto {
    @Schema(example = UUID_EXAMPLE)
    private String id;

    @SuppressWarnings("unused")
    UserDtoResponse(String id, String email, String firstName, String lastName,
                    LocalDate birthDate, String phoneNumber, Address address) {
        super(email, firstName, lastName, birthDate, phoneNumber, address);
        this.id = id;
    }
}
