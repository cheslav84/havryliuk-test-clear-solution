package com.havryliuk.test.users.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;


public record UserCreationDto (

        @NotNull
        @Valid
        UserDto data

) {}
