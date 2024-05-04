package com.havryliuk.test.users.dto.request;

import com.havryliuk.test.users.valitator.groups.FieldsRequired;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import static com.havryliuk.test.users.util.GlobalConstants.PROPERTY_REQUIRED;

@Builder
public record DataUserDto(
        @NotNull(message = PROPERTY_REQUIRED, groups = {FieldsRequired.class})
        @Valid
        UserDto data
) {}
