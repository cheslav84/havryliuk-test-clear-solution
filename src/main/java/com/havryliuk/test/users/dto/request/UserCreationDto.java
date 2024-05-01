package com.havryliuk.test.users.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import static com.havryliuk.test.users.valitator.ValidatorConstants.DATA_REQUIRED;

public record UserCreationDto (
        @NotNull(message = DATA_REQUIRED)
        @Valid
        UserDto data
) {}
