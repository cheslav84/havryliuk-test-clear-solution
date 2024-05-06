package com.havryliuk.test.users.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.havryliuk.test.users.util.GlobalConstants.BIRTH_DATE;
import static com.havryliuk.test.users.util.GlobalConstants.EMAIL;
import static com.havryliuk.test.users.util.GlobalConstants.FIRST_NAME;
import static com.havryliuk.test.users.util.GlobalConstants.LAST_NAME;
import static com.havryliuk.test.users.util.GlobalConstants.URL_WITH_UUID_EXAMPLE;
import static com.havryliuk.test.users.util.GlobalConstants.USERS_URL_ID;
import static com.havryliuk.test.users.util.GlobalConstants.UUID_EXAMPLE;

//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public final class UserShortDtoResponse {
//    @Schema(example = UUID_EXAMPLE)
//    private String id;
//    @Schema(example = EMAIL)
//    private String email;
//    @Schema(example = FIRST_NAME)
//    private String firstName;
//    @Schema(example = LAST_NAME)
//    private String lastName;
//    @Schema(example = BIRTH_DATE)
//    private LocalDate birthDate;
//    @Schema(example = URL_WITH_UUID_EXAMPLE)
//    private String link;

//    @SuppressWarnings("unused")
//    @Builder
//    UserShortDtoResponse(String id, String email, String firstName, String lastName, LocalDate birthDate) {
//        this.id = id;
//        this.email = email;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.birthDate = birthDate;
//        this.link = String.format(USERS_URL_ID, id);
//    }
//}


@Builder
public record UserShortDtoResponse(
    @Schema(example = UUID_EXAMPLE)
    String id,
    @Schema(example = EMAIL)
    String email,
    @Schema(example = FIRST_NAME)
    String firstName,
    @Schema(example = LAST_NAME)
    String lastName,
    @Schema(example = BIRTH_DATE)
    LocalDate birthDate,
    @Schema(example = URL_WITH_UUID_EXAMPLE)
    String link

){}