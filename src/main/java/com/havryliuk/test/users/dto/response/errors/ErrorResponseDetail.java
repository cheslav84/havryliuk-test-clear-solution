package com.havryliuk.test.users.dto.response.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.With;

@With
@Builder
public record ErrorResponseDetail(
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String property,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String cause,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String message
        ) {
}