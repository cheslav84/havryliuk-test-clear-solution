package com.havryliuk.test.users.service;

import com.havryliuk.test.users.dto.request.UserCreationDto;

public interface UserService {
    String create(UserCreationDto user);
}
