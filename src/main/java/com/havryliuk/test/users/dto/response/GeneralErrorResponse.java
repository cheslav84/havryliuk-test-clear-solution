package com.havryliuk.test.users.dto.response;

import lombok.Builder;
import lombok.With;

import java.time.Instant;

@With
@Builder
public record GeneralErrorResponse (
                Instant timestamp,
                int statusCode,
                String error,
                String message,
                String path
                ) {
}