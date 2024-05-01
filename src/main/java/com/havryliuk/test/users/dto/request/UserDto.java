package com.havryliuk.test.users.dto.request;

import com.havryliuk.test.users.model.Address;
import com.havryliuk.test.users.valitator.ValidAge;
import com.havryliuk.test.users.valitator.ValidEmail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static com.havryliuk.test.users.swager.Constants.EMAIL_EXAMPLE;
import static com.havryliuk.test.users.valitator.ValidatorConstants.BIRTH_DATE_REQUIRED;
import static com.havryliuk.test.users.valitator.ValidatorConstants.FIRST_NAME_REQUIRED;
import static com.havryliuk.test.users.valitator.ValidatorConstants.LAST_NAME_REQUIRED;

record UserDto (

    @NotNull
    @ValidEmail
    @Schema(example = EMAIL_EXAMPLE)
    String email,

    @NotNull(message = FIRST_NAME_REQUIRED)
    @Size(max = 32, message = "First name should be maximum 32 characters long")
    @Schema(example = "FirstName")
    String firstName,

    @NotNull(message = LAST_NAME_REQUIRED)
    @Size(max = 32, message = "Last name should be maximum 32 characters long")
    @Schema(example = "Lastname")
    String lastName,

    @NotNull(message = BIRTH_DATE_REQUIRED)
    @ValidAge
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(example = "2006-05-01")
    LocalDate birthDate,

    @Schema(example = "+30961114545")
    @Size(max = 16, message = "Phone number should be maximum characters long")
            //todo get pattern
    String phoneNumber,

    @Valid
    Address address

){}