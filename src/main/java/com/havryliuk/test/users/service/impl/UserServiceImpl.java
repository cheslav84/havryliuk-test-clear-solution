package com.havryliuk.test.users.service.impl;

import com.havryliuk.test.users.dto.UserDtoResponse;
import com.havryliuk.test.users.dto.request.BirthdayRangeDto;
import com.havryliuk.test.users.dto.request.DataUserDto;
import com.havryliuk.test.users.dto.response.UserShortDtoResponse;
import com.havryliuk.test.users.exception.NotFoundException;
import com.havryliuk.test.users.model.User;
import com.havryliuk.test.users.repository.UserRepository;
import com.havryliuk.test.users.service.UserService;
import com.havryliuk.test.users.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.havryliuk.test.users.util.GlobalConstants.BIRTH_DATE_FIELD;
import static com.havryliuk.test.users.util.GlobalConstants.USER_FIELD;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    @Transactional
    public String create(DataUserDto userDto) {
        User user = UserMapper.toUser(UUID.randomUUID().toString(), userDto.data());
        repository.save(user);
        return user.getId();
    }

    @Override
    @Transactional
    public void updateFields(String id, DataUserDto userDto) {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException(USER_FIELD));
        UserMapper.patchUser(user, userDto.data());

    }

    @Override
    @Transactional
    public void updateWhole(String id, DataUserDto userDto) {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException(USER_FIELD));
        user = UserMapper.toUser(user.getId(), userDto.data());
        repository.save(user);
    }

    @Override
    @Transactional
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDtoResponse find(String id) {
        return repository.findUserResponseDtoById(id).orElseThrow(() -> new NotFoundException(USER_FIELD));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserShortDtoResponse> find(BirthdayRangeDto birthDateRange, Integer number, Integer size) {
        Sort sort = Sort.by(BIRTH_DATE_FIELD);
        Pageable pageable = PageRequest.of(number, size, sort);
        return repository.findAllByBirthDateBetween(UserShortDtoResponse.class, pageable,
                birthDateRange.birthDateFrom(),  birthDateRange.birthDateTo());
    }

}
