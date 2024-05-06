package com.havryliuk.test.users.service;

import com.havryliuk.test.users.dto.request.BirthdayRangeDto;
import com.havryliuk.test.users.dto.request.DataUserDto;
import com.havryliuk.test.users.dto.response.DataUsersDto;
import com.havryliuk.test.users.dto.response.UserDtoResponse;

public interface UserService {
    String create(DataUserDto user);
    void updateFields(String id, DataUserDto user);
    void updateWhole(String id, DataUserDto user);
    void delete(String id);
    UserDtoResponse find(String id);
    DataUsersDto find(BirthdayRangeDto birthDateRange, Integer number, Integer size);
}
