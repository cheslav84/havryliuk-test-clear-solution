package com.havryliuk.test.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static com.havryliuk.test.users.util.GlobalConstants.UUID_EXAMPLE;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public final class UserDtoResponse extends UserDto {
    @Schema(example = UUID_EXAMPLE)
    private String id;
}
