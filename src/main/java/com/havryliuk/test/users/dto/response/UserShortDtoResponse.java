package com.havryliuk.test.users.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;

import static com.havryliuk.test.users.util.GlobalConstants.BIRTH_DATE;
import static com.havryliuk.test.users.util.GlobalConstants.EMAIL;
import static com.havryliuk.test.users.util.GlobalConstants.FIRST_NAME;
import static com.havryliuk.test.users.util.GlobalConstants.LAST_NAME;
import static com.havryliuk.test.users.util.GlobalConstants.URL_WITH_UUID_EXAMPLE;
import static com.havryliuk.test.users.util.GlobalConstants.UUID_EXAMPLE;


@Builder
public record UserShortDtoResponse(
    @Schema(example = UUID_EXAMPLE)
    String id,
    @Schema(example = EMAIL)
    String email,
    @Schema(example = FIRST_NAME)
    String firstName,
    @Schema(example = LAST_NAME)
    String lastName,
    @Schema(example = BIRTH_DATE)
    LocalDate birthDate,
    @Schema(example = URL_WITH_UUID_EXAMPLE)
    String link

){}