package com.havryliuk.test.users.swager;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.havryliuk.test.users.dto.response.UserShortDtoResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

record PaginationSchema (
        @Schema
        @JsonProperty
        Page<UserShortDtoResponse> page
){
    @SuppressWarnings("unused")
    public PaginationSchema {
    }
}
