package com.havryliuk.test.users.dto.response;

import lombok.Builder;
import org.hibernate.query.Page;

import java.util.List;


@Builder
public record DataUsersDto(
        List<UserShortDtoResponse> data,
        Page page
) {}
