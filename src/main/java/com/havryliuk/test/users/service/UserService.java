package com.havryliuk.test.users.service;

import com.havryliuk.test.users.dto.request.DataUserDto;

public interface UserService {
    String create(DataUserDto user);
    void updateFields(String id, DataUserDto user);
    void updateWhole(String id, DataUserDto user);
}
