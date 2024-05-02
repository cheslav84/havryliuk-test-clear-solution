package com.havryliuk.test.users.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import static com.havryliuk.test.users.valitator.ValidatorConstants.PROPERTY_REQUIRED;

@Builder
public record UserCreationDto (
        @NotNull(message = PROPERTY_REQUIRED)
        @Valid
        UserDto data
) {}
