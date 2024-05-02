package com.havryliuk.test.users.dto.response;

import lombok.Builder;
import lombok.With;

import java.time.Instant;
import java.util.List;

@With
@Builder
public record GeneralErrorResponse (
        Instant timestamp,
//        @Schema(example = SERVER_ERROR)
        String title,
//        @Schema(example = "500")
        int statusCode,
        List<ErrorResponseDetail> details,
//        @Schema(example = USER_CREATION_URL)
        String instance
) {
}