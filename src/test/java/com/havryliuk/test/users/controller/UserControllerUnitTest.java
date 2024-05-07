package com.havryliuk.test.users.controller;

import com.havryliuk.test.users.dto.UserDtoResponse;
import com.havryliuk.test.users.dto.request.BirthdayRangeDto;
import com.havryliuk.test.users.dto.request.DataUserDto;
import com.havryliuk.test.users.dto.response.UserShortDtoResponse;
import com.havryliuk.test.users.service.impl.UserServiceImpl;
import com.havryliuk.test.users.util.DtoCreator;
import com.havryliuk.test.users.util.HttpReasonResolver;
import com.havryliuk.test.users.util.JsonConverter;
import com.havryliuk.test.users.util.DataUserRequiredFieldsProvider;
import com.havryliuk.test.users.valitator.UserAgeValidator;
import com.havryliuk.test.users.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.UUID;

import static com.havryliuk.test.users.util.GlobalConstants.BIRTH_DATE_FROM_FIELD;
import static com.havryliuk.test.users.util.GlobalConstants.BIRTH_DATE_TO_FIELD;
import static com.havryliuk.test.users.util.GlobalConstants.NOT_COHERENT_DATES;
import static com.havryliuk.test.users.util.GlobalConstants.USER_FIELD;
import static com.havryliuk.test.users.util.GlobalConstants.DATA_NOT_FOUND;
import static com.havryliuk.test.users.util.GlobalConstants.LOCATION_HEADER;
import static com.havryliuk.test.users.util.GlobalConstants.SERVER_ERROR_MESSAGE;
import static com.havryliuk.test.users.util.GlobalConstants.USERS_URL;
import static com.havryliuk.test.users.util.GlobalConstants.USERS_URL_ID;
import static com.havryliuk.test.users.util.GlobalConstants.USERS_URL_BIRTHDATE_RANGE;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@MockBeans({
        @MockBean(UserServiceImpl.class)
})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class UserControllerUnitTest {
    private static final String ID = UUID.randomUUID().toString();
    private static final String MOCK_AGE_FIELD = "ALLOWED_AGE_T0_REGISTER";

    private final UserServiceImpl service;
    private final MockMvc mockMvc;
    @Mock
    private UserAgeValidator validator;


    @ParameterizedTest
    @MethodSource("com.havryliuk.test.users.util.DataUserRequiredFieldsProvider#provideValidUserDtos")
    void whenCreateUser_thenReturnResponseCreated(DataUserDto dto) throws Exception {
        when(service.create(any(DataUserDto.class))).thenReturn(ID);

        mockMvc.perform(post(USERS_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConverter.toString(dto)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.header()
                        .stringValues(LOCATION_HEADER, String.format(USERS_URL_ID, ID)));

        verify(service, times(1)).create(any(DataUserDto.class));
    }



    @ParameterizedTest
    @MethodSource("com.havryliuk.test.users.util.DataUserRequiredFieldsProvider#provideInvalidUserDtos")
    void whenCreateUser_thenReturnResponseError(DataUserDto dto,
                                                     int errors,
                                                     int status,
                                                     String[] parameters,
                                                     String[] causes,
                                                     String[] messages) throws Exception {

        ReflectionTestUtils.setField(validator, MOCK_AGE_FIELD, DataUserRequiredFieldsProvider.ALLOWED_AGE_T0_REGISTER);

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
                .andExpect(jsonPath("$.details[*].cause", hasItems(causes)))
                .andExpect(jsonPath("$.details[*].message", hasItems(messages)));

        verify(service, never()).create(any(DataUserDto.class));
    }


    @ParameterizedTest
    @MethodSource("com.havryliuk.test.users.util.DataUserRequiredFieldsProvider#provideValidUserDtos")
    void whenUpdateUser_withUserRequiredField_thenReturnResponseOk(DataUserDto dto) throws Exception {
        doNothing().when(service).updateFields(eq(ID), any(DataUserDto.class));

        mockMvc.perform(patch(String.format(USERS_URL_ID, ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConverter.toString(dto)))
                .andExpect(status().isOk());

        verify(service, times(1)).updateFields(eq(ID), any(DataUserDto.class));
    }


    @ParameterizedTest
    @MethodSource("com.havryliuk.test.users.util.DataUserOptionalFieldsProvider#provideValidUserDtos")
    void whenUpdateUser_withUserOptionalFields_thenReturnResponseOk(DataUserDto dto) throws Exception {
        doNothing().when(service).updateFields(eq(ID), any(DataUserDto.class));

        mockMvc.perform(patch(String.format(USERS_URL_ID, ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConverter.toString(dto)))
                .andExpect(status().isOk());

        verify(service, times(1)).updateFields(eq(ID), any(DataUserDto.class));
    }


    @ParameterizedTest
    @MethodSource("com.havryliuk.test.users.util.DataUserOptionalFieldsProvider#provideInvalidUserDtos")
    void whenUpdateUser_thenReturnResponseError(DataUserDto dto,
                                                int errors,
                                                int status,
                                                String[] parameters,
                                                String[] causes,
                                                String[] messages) throws Exception {

        ReflectionTestUtils.setField(validator, MOCK_AGE_FIELD, DataUserRequiredFieldsProvider.ALLOWED_AGE_T0_REGISTER);

        mockMvc.perform(patch(String.format(USERS_URL_ID, ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConverter.toString(dto)))
                .andExpect(status().is(status))
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.title", is(HttpReasonResolver.getReasonPhrase(status))))
                .andExpect(jsonPath("$.statusCode", is(status)))
                .andExpect(jsonPath("$.instance", is(String.format(USERS_URL_ID, ID))))
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details", hasSize(errors)))
                .andExpect(jsonPath("$.details[*].property", hasItems(parameters)))
                .andExpect(jsonPath("$.details[*].cause", hasItems(causes)))
                .andExpect(jsonPath("$.details[*].message", hasItems(messages)));

        verify(service, never()).updateFields(eq(ID), any(DataUserDto.class));
    }


    @ParameterizedTest
    @MethodSource("com.havryliuk.test.users.util.DataUserRequiredFieldsProvider#provideValidUserDtos")
    void whenReplaceUser_thenReturnResponseOk(DataUserDto dto) throws Exception {
        doNothing().when(service).updateWhole(eq(ID), any(DataUserDto.class));

        mockMvc.perform(put(String.format(USERS_URL_ID, ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConverter.toString(dto)))
                .andExpect(status().isOk());

        verify(service, times(1)).updateWhole(eq(ID), any(DataUserDto.class));
    }


    @ParameterizedTest
    @MethodSource("com.havryliuk.test.users.util.DataUserRequiredFieldsProvider#provideInvalidUserDtos")
    void whenReplaceUser_thenReturnResponseError(DataUserDto dto,
                                                int errors,
                                                int status,
                                                String[] parameters,
                                                String[] causes,
                                                String[] messages) throws Exception {

        ReflectionTestUtils.setField(validator, MOCK_AGE_FIELD, DataUserRequiredFieldsProvider.ALLOWED_AGE_T0_REGISTER);

        mockMvc.perform(put(String.format(USERS_URL_ID, ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConverter.toString(dto)))
                .andExpect(status().is(status))
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.title", is(HttpReasonResolver.getReasonPhrase(status))))
                .andExpect(jsonPath("$.statusCode", is(status)))
                .andExpect(jsonPath("$.instance", is(String.format(USERS_URL_ID, ID))))
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details", hasSize(errors)))
                .andExpect(jsonPath("$.details[*].property", hasItems(parameters)))
                .andExpect(jsonPath("$.details[*].cause", hasItems(causes)))
                .andExpect(jsonPath("$.details[*].message", hasItems(messages)));

        verify(service, never()).updateWhole(eq(ID), any(DataUserDto.class));
    }

    @Test
    void whenReplaceUser_thenReturnResponseNotFound() throws Exception {
        DataUserDto dto = DtoCreator.createValidUserDtoWithAllData();

        doThrow(new NotFoundException(USER_FIELD)).when(service).updateWhole(eq(ID), any(DataUserDto.class));

        mockMvc.perform(put(String.format(USERS_URL_ID, ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConverter.toString(dto)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.title", is(HttpReasonResolver.getReasonPhrase(404))))
                .andExpect(jsonPath("$.statusCode", is(404)))
                .andExpect(jsonPath("$.instance", is(String.format(USERS_URL_ID, ID))))
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details", hasSize(1)))
                .andExpect(jsonPath("$.details[*].property", hasItems(USER_FIELD)))
                .andExpect(jsonPath("$.details[*].cause", hasItems(DATA_NOT_FOUND)))
                .andExpect(jsonPath("$.details[*].message", hasItems(new String[]{})));

        verify(service, times(1)).updateWhole(eq(ID), any(DataUserDto.class));
    }

    @Test
    void whenDeleteUser_thenReturnResponseOk() throws Exception {
        doNothing().when(service).delete(eq(ID));

        mockMvc.perform(delete(String.format(USERS_URL_ID, ID)))
                .andExpect(status().isOk());

        verify(service, times(1)).delete(eq(ID));
    }

    @Test
    void whenDeleteUser_thenReturnResponseServerError() throws Exception {
        doThrow(new RuntimeException()).when(service).delete(eq(ID));

        mockMvc.perform(delete(String.format(USERS_URL_ID, ID)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.title", is(HttpReasonResolver.getReasonPhrase(500))))
                .andExpect(jsonPath("$.statusCode", is(500)))
                .andExpect(jsonPath("$.instance", is(String.format(USERS_URL_ID, ID))))
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details", hasSize(1)))
                .andExpect(jsonPath("$.details[*].property", hasItems(new String[]{})))
                .andExpect(jsonPath("$.details[*].cause", hasItems(new String[]{})))
                .andExpect(jsonPath("$.details[*].message", hasItems(SERVER_ERROR_MESSAGE)));

        verify(service, times(1)).delete(eq(ID));
    }


    @Test
    void whenFindUsersByBirthdateRange_thenReturnBirthdayRangeDto() throws Exception {
        Page<UserShortDtoResponse> users = DtoCreator.createPageOfUsers();
        BirthdayRangeDto dto = DtoCreator.createBirthdayRangeDto();
        LocalDate from = dto.birthDateFrom();
        LocalDate to = dto.birthDateTo();

        when(service.find(dto, 0, 5)).thenReturn(users);

        mockMvc.perform(get(String.format(USERS_URL_BIRTHDATE_RANGE, from, to)))
                .andExpect(status().isOk());

        verify(service, times(1)).find(dto, 0, 5);
    }

    @Test
    void whenFindUsersByBirthdateRange_thenReturnResponseBadRequest_IncoherentDates() throws Exception {
        LocalDate date = LocalDate.of(2005, 4, 5);
        String url = String.format(USERS_URL_BIRTHDATE_RANGE, date, date);
        mockMvc.perform(get(url))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.title", is(HttpReasonResolver.getReasonPhrase(400))))
                .andExpect(jsonPath("$.statusCode", is(400)))
                .andExpect(jsonPath("$.instance", is(url)))
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details", hasSize(2)))
                .andExpect(jsonPath("$.details[*].property", hasItems(BIRTH_DATE_FROM_FIELD, BIRTH_DATE_TO_FIELD)))
                .andExpect(jsonPath("$.details[*].cause", hasItems(NOT_COHERENT_DATES)))
                .andExpect(jsonPath("$.details[*].message", hasItems(new String[]{})));

        verify(service, never()).find(any(BirthdayRangeDto.class), any(Integer.class), any(Integer.class));
    }


    @Test
    void whenFindUsersById_thenReturnUserDto() throws Exception {
        String id = UUID.randomUUID().toString();
        UserDtoResponse user = DtoCreator.createUserDto(id);

        when(service.find(eq(id))).thenReturn(user);

        mockMvc.perform(get(String.format(USERS_URL_ID, id)))
                .andExpect(status().isOk());

        verify(service, times(1)).find(id);
    }

    @Test
    void whenFindUsersById_thenReturnResponseNotFound() throws Exception {
        doThrow(new NotFoundException(USER_FIELD)).when(service).find(eq(ID));

        mockMvc.perform(get(String.format(USERS_URL_ID, ID)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.title", is(HttpReasonResolver.getReasonPhrase(404))))
                .andExpect(jsonPath("$.statusCode", is(404)))
                .andExpect(jsonPath("$.instance", is(String.format(USERS_URL_ID, ID))))
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details", hasSize(1)))
                .andExpect(jsonPath("$.details[*].property", hasItems(USER_FIELD)))
                .andExpect(jsonPath("$.details[*].cause", hasItems(DATA_NOT_FOUND)))
                .andExpect(jsonPath("$.details[*].message", hasItems(new String[]{})));

        verify(service, times(1)).find(eq(ID));
    }
}