package com.havryliuk.test.users.service.impl;

import com.havryliuk.test.users.dto.request.DataUserDto;
import com.havryliuk.test.users.repository.UserRepository;
import com.havryliuk.test.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    @Transactional
    public String create(DataUserDto userDto) {
        String id = UUID.randomUUID().toString();
//        throw new RuntimeException();

        return id;
    }

    @Override
    public void updateFields(String id, DataUserDto user) {

    }

    @Override
    public void updateWhole(String id, DataUserDto user) {

    }

}
