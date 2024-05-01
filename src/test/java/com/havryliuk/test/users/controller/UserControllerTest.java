package com.havryliuk.test.users.controller;

import com.havryliuk.test.users.dto.request.UserCreationDto;
import com.havryliuk.test.users.service.impl.UserServiceImpl;
import com.havryliuk.test.users.util.DtoCreator;
import com.havryliuk.test.users.util.JsonConverter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;
import java.util.stream.Stream;

import static com.havryliuk.test.users.util.GlobalConstants.LOCATION_HEADER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@MockBeans(
        @MockBean(UserServiceImpl.class)
)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class UserControllerTest {
    private static final String CREATE_USER_URL = "/v1/users";

    private final UserServiceImpl service;
    private final MockMvc mockMvc;

    @ParameterizedTest
//    @NullSource
    @MethodSource("provideValidUseCreationDtos")
    void whenCreateUser_thenReturnResponseCreated(UserCreationDto dto) throws Exception {
        String id = UUID.randomUUID().toString();
        when(service.create(any(UserCreationDto.class))).thenReturn(id);

        mockMvc.perform(post(CREATE_USER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConverter.toString(dto)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.header()
                        .stringValues(LOCATION_HEADER, CREATE_USER_URL + "/" + id));
    }

    private static Stream<Arguments> provideValidUseCreationDtos() {
        UserCreationDto withAllData = DtoCreator.createValidUserCreationDtoWithAllData();
        UserCreationDto withoutPhone = DtoCreator.createValidUserCreationDtoWithoutPhone();
        UserCreationDto withoutAddress = DtoCreator.createValidUserCreationDtoWithoutAddress();
        UserCreationDto withAddressWithoutCountry = DtoCreator.createValidUserCreationDtoAddressWithoutCountry();
        UserCreationDto withOnlyRequiredFields = DtoCreator.createValidUserCreationDtoWithOnlyRequiredFields();
        return Stream.of(
                Arguments.of(withAllData),
                Arguments.of(withoutPhone),
                Arguments.of(withoutAddress),
                Arguments.of(withAddressWithoutCountry),
                Arguments.of(withOnlyRequiredFields)

        );
    }

}