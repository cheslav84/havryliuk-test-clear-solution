package com.havryliuk.test.users.dto.request;

import com.havryliuk.test.users.valitator.ValidDateRange;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import java.time.LocalDate;

import static com.havryliuk.test.users.util.GlobalConstants.BIRTH_DATE;
import static com.havryliuk.test.users.util.GlobalConstants.BIRTH_DATE_FROM;
import static com.havryliuk.test.users.util.GlobalConstants.PROPERTY_REQUIRED;

@Builder
@ValidDateRange
public record BirthdayRangeDto(
    @NotNull(message = PROPERTY_REQUIRED)
    @Schema(example = BIRTH_DATE_FROM)
    LocalDate birthDateFrom,
    @NotNull(message = PROPERTY_REQUIRED)
    @Schema(example = BIRTH_DATE)
    LocalDate birthDateTo

){}