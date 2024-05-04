package com.havryliuk.test.users.controller;

import com.havryliuk.test.users.dto.request.DataUserDto;
import com.havryliuk.test.users.service.impl.UserServiceImpl;
import com.havryliuk.test.users.util.HttpReasonResolver;
import com.havryliuk.test.users.util.JsonConverter;
import com.havryliuk.test.users.util.DataUserCreationProvider;
import com.havryliuk.test.users.valitator.UserAgeValidator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static com.havryliuk.test.users.util.GlobalConstants.LOCATION_HEADER;
import static com.havryliuk.test.users.util.GlobalConstants.USERS_URL;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@MockBeans({
        @MockBean(UserServiceImpl.class)
})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class UserControllerTest {
    private final UserServiceImpl service;
    private final MockMvc mockMvc;
    @Mock
    private UserAgeValidator validator;


    @ParameterizedTest
    @MethodSource("com.havryliuk.test.users.util.DataUserCreationProvider#provideValidUserCreationDtos")
    void whenCreateUser_thenReturnResponseCreated(DataUserDto dto) throws Exception {
        String id = UUID.randomUUID().toString();
        when(service.create(any(DataUserDto.class))).thenReturn(id);

        mockMvc.perform(post(USERS_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConverter.toString(dto)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.header()
                        .stringValues(LOCATION_HEADER, USERS_URL + "/" + id));
    }



    @ParameterizedTest
//    @NullSource
    @MethodSource("com.havryliuk.test.users.util.DataUserCreationProvider#provideInvalidUserCreationDtos")
    void whenCreateUser_thenReturnResponseBadRequest(DataUserDto dto,
                                                     int errors,
                                                     int status,
                                                     String[] parameters,
                                                     String[] causes,
                                                     String[] messages) throws Exception {

        ReflectionTestUtils.setField(validator, "ALLOWED_AGE_T0_REGISTER", DataUserCreationProvider.ALLOWED_AGE_T0_REGISTER);

        mockMvc.perform(post(USERS_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConverter.toString(dto)))
                .andExpect(status().is(status))
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.title", is(HttpReasonResolver.getReasonPhrase(status))))
                .andExpect(jsonPath("$.statusCode", is(status)))
                .andExpect(jsonPath("$.instance", is(USERS_URL)))
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details", hasSize(errors)))
                .andExpect(jsonPath("$.details[*].property", hasItems(parameters)))
                .andExpect(jsonPath("$.details[*].cause", containsInAnyOrder(causes)))
                .andExpect(jsonPath("$.details[*].message", hasItems(messages)));
    }

}