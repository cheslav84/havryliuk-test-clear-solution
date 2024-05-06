package com.havryliuk.test.users.controller;

import com.havryliuk.test.users.dto.UserDtoResponse;
import com.havryliuk.test.users.dto.request.BirthdayRangeDto;
import com.havryliuk.test.users.dto.request.DataUserDto;
import com.havryliuk.test.users.dto.response.DataUsersDto;
import com.havryliuk.test.users.service.UserService;
import com.havryliuk.test.users.swager.UserControllerSwaggerDescriptor;
import com.havryliuk.test.users.valitator.groups.FieldsRequired;
import com.havryliuk.test.users.valitator.groups.OptionalFields;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.havryliuk.test.users.util.GlobalConstants.LOCATION_HEADER;
import static com.havryliuk.test.users.util.GlobalConstants.USERS_URL;
import static com.havryliuk.test.users.util.GlobalConstants.USERS_URL_BIRTHDATE_RANGE;
import static com.havryliuk.test.users.util.GlobalConstants.USERS_URL_ID;

@Slf4j
@Valid
@RestController
@RequiredArgsConstructor
@RequestMapping(USERS_URL)
public class UserController implements UserControllerSwaggerDescriptor {

    private final UserService userService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Validated({FieldsRequired.class}) @RequestBody DataUserDto user,
                           HttpServletResponse response) {
        log.info("POST {}", USERS_URL);
        String id = userService.create(user);
        response.addHeader(LOCATION_HEADER, String.format(USERS_URL_ID, id));
    }

    @Override
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserFields(@Validated({OptionalFields.class}) @RequestBody DataUserDto user,
                                 @PathVariable String id) {
        log.info("PATCH {}", String.format(USERS_URL_ID, id));
        userService.updateFields(id, user);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateWholeUser(@Validated({FieldsRequired.class}) @RequestBody DataUserDto user,
                                @PathVariable String id) {
        log.info("PUT {}", String.format(USERS_URL_ID, id));
        userService.updateWhole(id, user);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String id) {
        log.info("DELETE {}", String.format(USERS_URL_ID, id));
        userService.delete(id);
    }


    @Override
    @GetMapping("/birthdate-range")
    @ResponseStatus(HttpStatus.OK)
    public DataUsersDto findUsersByBirthdateRange(@Valid BirthdayRangeDto birthDateRange,
                                                  @RequestParam(defaultValue = "1") Integer number,
                                                  @RequestParam(defaultValue = "5") Integer size
                                                  ) {
        log.info("GET {}", String.format(USERS_URL_BIRTHDATE_RANGE, birthDateRange.birthDateFrom(), birthDateRange.birthDateTo()));
        return userService.find(birthDateRange, number, size);

    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDtoResponse findUsersById(@PathVariable String id) {
        log.info("GET {}", String.format(USERS_URL_ID, id));
        return userService.find(id);
    }

}
