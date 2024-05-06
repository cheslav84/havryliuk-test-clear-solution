package com.havryliuk.test.users.service;

import com.havryliuk.test.users.dto.UserDtoResponse;
import com.havryliuk.test.users.dto.request.BirthdayRangeDto;
import com.havryliuk.test.users.dto.request.DataUserDto;
import com.havryliuk.test.users.dto.response.UserShortDtoResponse;
import org.springframework.data.domain.Page;

public interface UserService {
    String create(DataUserDto user);
    void updateFields(String id, DataUserDto user);
    void updateWhole(String id, DataUserDto user);
    void delete(String id);
    UserDtoResponse find(String id);
    Page<UserShortDtoResponse> find(BirthdayRangeDto birthDateRange, Integer number, Integer size);
}
