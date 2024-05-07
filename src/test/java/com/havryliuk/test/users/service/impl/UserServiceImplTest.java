package com.havryliuk.test.users.service.impl;

import com.havryliuk.test.users.dto.UserDtoResponse;
import com.havryliuk.test.users.dto.request.BirthdayRangeDto;
import com.havryliuk.test.users.dto.request.DataUserDto;
import com.havryliuk.test.users.dto.response.UserShortDtoResponse;
import com.havryliuk.test.users.exception.NotFoundException;
import com.havryliuk.test.users.model.Address;
import com.havryliuk.test.users.model.User;
import com.havryliuk.test.users.repository.UserRepository;
import com.havryliuk.test.users.util.DataCreator;
import com.havryliuk.test.users.util.DtoCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

import static com.havryliuk.test.users.util.GlobalConstants.TESTING_UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    public void whenCreateUser_thenReturnId() {
        DataUserDto userDto = DtoCreator.createValidUserDtoWithAllData();
        String id = userService.create(userDto);
        verify(userRepository, times(1)).save(ArgumentMatchers.any(User.class));
        assertNotNull(id);
    }

    @Test
    public void whenUpdateFields_thenExpectNewFieldValues() {
        String lastName = "Doe";
        String country = "US";
        DataUserDto userDto = DtoCreator.createUserDtoWithLastNameAndAddressCounty(lastName, country);
        User user = DataCreator.createUser();

        when(userRepository.findById(TESTING_UUID)).thenReturn(Optional.of(user));

        assertNotEquals(user.getLastName(), lastName);
        assertNotEquals(user.getAddress().getCountry(), country);

        userService.updateFields(TESTING_UUID, userDto);

        assertEquals(user.getLastName(), lastName);
        assertEquals(user.getAddress().getCountry(), country);
    }

    @Test
    public void whenUpdateFields_setAddress_thenExpectNewAddress() {
        DataUserDto userDto = DtoCreator.createValidUserDtoWithAllData();
        User user = DataCreator.createUser();
        user.setAddress(null);

        when(userRepository.findById(TESTING_UUID)).thenReturn(Optional.of(user));
        userService.updateFields(TESTING_UUID, userDto);

        assertEquals(userDto.data().getAddress(), user.getAddress());
    }

    @Test
    public void whenUpdateFields_withoutAddress_thenExpectOldAddress() {
        DataUserDto userDto = DtoCreator.createValidUserDtoWithoutAddress();
        User user = DataCreator.createUser();
        Address beforeUpdating = user.getAddress();

        when(userRepository.findById(TESTING_UUID)).thenReturn(Optional.of(user));
        userService.updateFields(TESTING_UUID, userDto);

        assertEquals(beforeUpdating, user.getAddress());
    }


    @Test
    public void whenUpdateFields_thenExpectNotFoundException() {
        assertThrows(NotFoundException.class, () -> userService.updateFields(TESTING_UUID, any()));
    }


    @Test
    public void testUpdateWholeUser_thenExpectNewFieldValues() {
        DataUserDto userDto = DtoCreator.createValidUserDtoWithAllData();
        userDto.data().getAddress().setZipcode(null);
        User user = DataCreator.createUser();
        user.getAddress().setCity(null);
        user.setPhoneNumber(null);

        when(userRepository.findById(TESTING_UUID)).thenReturn(Optional.of(user));
        userService.updateWhole(TESTING_UUID, userDto);

        assertEquals(userDto.data().getAddress().getCity(), user.getAddress().getCity());
        assertEquals(userDto.data().getAddress().getZipcode(), user.getAddress().getZipcode());
        assertEquals(userDto.data().getPhoneNumber(), user.getPhoneNumber());
        assertEquals(userDto.data().getPhoneNumber(), user.getPhoneNumber());
    }

    @Test
    public void whenUpdateWholeUser_setAddress_thenExpectNewAddress() {
        DataUserDto userDto = DtoCreator.createValidUserDtoWithAllData();
        User user = DataCreator.createUser();
        user.setAddress(null);

        when(userRepository.findById(TESTING_UUID)).thenReturn(Optional.of(user));
        userService.updateWhole(TESTING_UUID, userDto);

        assertEquals(userDto.data().getAddress(), user.getAddress());
    }


    @Test
    public void whenUpdateWholeUser_deleteAddress_thenExpectNewAddress() {
        DataUserDto userDto = DtoCreator.createValidUserDtoWithoutAddress();
        User user = DataCreator.createUser();

        when(userRepository.findById(TESTING_UUID)).thenReturn(Optional.of(user));
        userService.updateWhole(TESTING_UUID, userDto);

        assertEquals(userDto.data().getAddress(), user.getAddress());
    }

    @Test
    public void whenUpdateWholeUser_thenExpectNotFoundException() {
        assertThrows(NotFoundException.class, () -> userService.updateWhole(TESTING_UUID, any()));
    }

    @Test
    public void whenDeleteUser_thenCallDelete() {
        userService.delete(TESTING_UUID);
        verify(userRepository).deleteById(TESTING_UUID);
    }

    @Test
    public void whenFindUser_thenSuccess() {
        UserDtoResponse userDto = DtoCreator.createUserDto(TESTING_UUID);
        when(userRepository.findUserResponseDtoById(TESTING_UUID)).thenReturn(Optional.of(userDto));
        UserDtoResponse foundUser = userService.find(TESTING_UUID);
        assertEquals(userDto, foundUser);
    }

    @Test
    public void whenFindUser_thenExpectNotFoundException() {
        assertThrows(NotFoundException.class, () -> userService.find(TESTING_UUID));
    }


    @Test
    public void testFindUsersByBirthDateRange() {
        BirthdayRangeDto birthDateRange = DtoCreator.createBirthdayRangeDto();
        Page<UserShortDtoResponse> expected = DtoCreator.createPageOfUsers();

        when(userRepository.findAllByBirthDateBetween(
                eq(UserShortDtoResponse.class),
                ArgumentMatchers.any(Pageable.class),
                eq(birthDateRange.birthDateFrom()),
                eq(birthDateRange.birthDateTo()))
        ).thenReturn(expected);

        Page<UserShortDtoResponse> foundUsers = userService.find(birthDateRange, 0, 5);

        assertEquals(2, foundUsers.getTotalElements());
        assertTrue(foundUsers.isFirst());
    }
}