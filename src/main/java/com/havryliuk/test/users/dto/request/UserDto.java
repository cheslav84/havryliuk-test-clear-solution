package com.havryliuk.test.users.dto.request;

import com.havryliuk.test.users.model.Address;
import com.havryliuk.test.users.valitator.ValidAge;
import com.havryliuk.test.users.valitator.ValidEmail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static com.havryliuk.test.users.util.GlobalConstants.BIRTH_DATE;
import static com.havryliuk.test.users.util.GlobalConstants.EMAIL;
import static com.havryliuk.test.users.util.GlobalConstants.FIRST_NAME;
import static com.havryliuk.test.users.util.GlobalConstants.LAST_NAME;
import static com.havryliuk.test.users.util.GlobalConstants.PHONE_NUMBER;
import static com.havryliuk.test.users.valitator.ValidatorConstants.FIRST_NAME_REQUIRED;
import static com.havryliuk.test.users.valitator.ValidatorConstants.INCORRECT_PHONE_NUMBER;
import static com.havryliuk.test.users.valitator.ValidatorConstants.LAST_NAME_REQUIRED;
import static com.havryliuk.test.users.valitator.ValidatorConstants.PHONE_PATTERN;

@Builder
public record UserDto (
    @ValidEmail
    @Schema(example = EMAIL)
    String email,

    @NotNull(message = FIRST_NAME_REQUIRED)
    @Size(max = 32, message = "First name should be maximum 32 characters long")
    @Schema(example = FIRST_NAME)
    String firstName,

    @NotNull(message = LAST_NAME_REQUIRED)
    @Size(max = 32, message = "Last name should be maximum 32 characters long")
    @Schema(example = LAST_NAME)
    String lastName,

    @ValidAge
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(example = BIRTH_DATE)
    LocalDate birthDate,

    @Schema(example = PHONE_NUMBER)
    @Pattern(regexp = PHONE_PATTERN, message = INCORRECT_PHONE_NUMBER)
    String phoneNumber,

    @Valid
    Address address

){}