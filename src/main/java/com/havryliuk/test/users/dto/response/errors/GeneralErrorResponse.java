package com.havryliuk.test.users.dto.response.errors;

import lombok.Builder;
import lombok.With;

import java.time.Instant;
import java.util.List;

@With
@Builder
public record GeneralErrorResponse (
        Instant timestamp,
        String title,
        int statusCode,
        List<ErrorResponseDetail> details,
        String instance
) {
}