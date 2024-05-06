package com.havryliuk.test.users.service.impl;

import com.havryliuk.test.users.dto.UserDtoResponse;
import com.havryliuk.test.users.dto.request.BirthdayRangeDto;
import com.havryliuk.test.users.dto.request.DataUserDto;
import com.havryliuk.test.users.dto.response.DataUsersDto;
import com.havryliuk.test.users.dto.response.UserShortDtoResponse;
import com.havryliuk.test.users.exception.NotFoundException;
import com.havryliuk.test.users.model.User;
import com.havryliuk.test.users.repository.UserRepository;
import com.havryliuk.test.users.service.UserService;
import com.havryliuk.test.users.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static com.havryliuk.test.users.util.GlobalConstants.USER_FIELD;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    @Transactional
    public String create(DataUserDto userDto) {
        User user = UserMapper.toUser(userDto.data());
        repository.save(user);
        return user.getId();
    }

    @Override
    @Transactional
    public void updateFields(String id, DataUserDto user) {

    }

    @Override
    @Transactional
    public void updateWhole(String id, DataUserDto user) {

    }

    @Override
    @Transactional
    public void delete(String id) {

    }

    @Override
    @Transactional(readOnly = true)
    public UserDtoResponse find(String id) {
        return repository.findUserResponseDtoById(id).orElseThrow(() -> new NotFoundException(USER_FIELD));
    }

    @Override
    @Transactional(readOnly = true)
    public DataUsersDto find(BirthdayRangeDto birthDateRange, Integer number, Integer size) {

        List<UserShortDtoResponse> list = repository.findAllBy(UserShortDtoResponse.class);
//        List<UserShortDtoResponse> list = repository.findAll();

return null;
//        throw new UnsupportedOperationException();
    }

}
