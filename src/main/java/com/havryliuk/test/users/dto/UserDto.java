package com.havryliuk.test.users.dto;

import com.havryliuk.test.users.model.Address;
import com.havryliuk.test.users.valitator.groups.FieldsRequired;
import com.havryliuk.test.users.valitator.ValidAge;
import com.havryliuk.test.users.valitator.groups.OptionalFields;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static com.havryliuk.test.users.util.GlobalConstants.BIRTH_DATE;
import static com.havryliuk.test.users.util.GlobalConstants.EMAIL;
import static com.havryliuk.test.users.util.GlobalConstants.FIRST_NAME;
import static com.havryliuk.test.users.util.GlobalConstants.LAST_NAME;
import static com.havryliuk.test.users.util.GlobalConstants.PHONE_NUMBER;
import static com.havryliuk.test.users.util.GlobalConstants.LONG_PROPERTY_32;
import static com.havryliuk.test.users.util.GlobalConstants.NOT_VALID_EMAIL;
import static com.havryliuk.test.users.util.GlobalConstants.PROPERTY_REQUIRED;
import static com.havryliuk.test.users.util.GlobalConstants.INCORRECT_PHONE_NUMBER;
import static com.havryliuk.test.users.util.GlobalConstants.PHONE_PATTERN;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public sealed class UserDto permits UserDtoResponse {

    @NotNull(message = PROPERTY_REQUIRED, groups = {FieldsRequired.class})
    @Email(message = NOT_VALID_EMAIL, groups = {FieldsRequired.class, OptionalFields.class})
    @Schema(example = EMAIL)
    private String email;

    @NotNull(message = PROPERTY_REQUIRED, groups = FieldsRequired.class)
    @Size(max = 32, message = LONG_PROPERTY_32, groups = {FieldsRequired.class, OptionalFields.class})
    @Schema(example = FIRST_NAME)
    private String firstName;

    @NotNull(message = PROPERTY_REQUIRED, groups = FieldsRequired.class)
    @Size(max = 32, message = LONG_PROPERTY_32, groups = {FieldsRequired.class, OptionalFields.class})
    @Schema(example = LAST_NAME)
    private String lastName;

    @NotNull(message = PROPERTY_REQUIRED, groups = FieldsRequired.class)
    @ValidAge(groups = {FieldsRequired.class, OptionalFields.class})
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(example = BIRTH_DATE)
    private LocalDate birthDate;

    @Pattern(regexp = PHONE_PATTERN, message = INCORRECT_PHONE_NUMBER, groups = {FieldsRequired.class, OptionalFields.class})
    @Schema(example = PHONE_NUMBER)
    private String phoneNumber;

    @Valid
    private Address address;

}