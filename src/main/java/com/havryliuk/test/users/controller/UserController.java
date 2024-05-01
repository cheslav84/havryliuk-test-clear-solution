package com.havryliuk.test.users.controller;

import com.havryliuk.test.users.dto.request.UserCreationDto;
import com.havryliuk.test.users.service.UserService;
import com.havryliuk.test.users.swager.UserControllerSwaggerDescriptor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.havryliuk.test.users.util.GlobalConstants.LOCATION_HEADER;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController implements UserControllerSwaggerDescriptor {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody UserCreationDto user, HttpServletResponse response) {
        log.info("POST /v1/users");
        String id = userService.create(user);
        response.addHeader(LOCATION_HEADER, "/v1/users/" + id);
    }



}
