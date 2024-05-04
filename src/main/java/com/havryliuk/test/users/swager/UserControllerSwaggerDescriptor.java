package com.havryliuk.test.users.swager;
import com.havryliuk.test.users.dto.request.DataUserDto;
import com.havryliuk.test.users.dto.response.GeneralErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import static com.havryliuk.test.users.util.GlobalConstants.BAD_REQUEST;
import static com.havryliuk.test.users.util.GlobalConstants.CREATED;
import static com.havryliuk.test.users.util.GlobalConstants.FORBIDDEN;
import static com.havryliuk.test.users.util.GlobalConstants.LOCATION_HEADER;
import static com.havryliuk.test.users.util.GlobalConstants.NOT_FOUND;
import static com.havryliuk.test.users.util.GlobalConstants.OK;
import static com.havryliuk.test.users.util.GlobalConstants.SERVER_ERROR;
import static com.havryliuk.test.users.util.GlobalConstants.USER_CREATED_HEADER_DESCRIPTION;
import static com.havryliuk.test.users.swager.ErrorResponseConstants.INCORRECT_DATA_RESPONSE;
import static com.havryliuk.test.users.swager.ErrorResponseConstants.INCORRECT_DATA_RESPONSE_ID;
import static com.havryliuk.test.users.swager.ErrorResponseConstants.LONG_PROPERTY_RESPONSE_ID;
import static com.havryliuk.test.users.swager.ErrorResponseConstants.NOT_VALID_EMAIL_RESPONSE_ID;
import static com.havryliuk.test.users.swager.ErrorResponseConstants.PROPERTY_REQUIRED_RESPONSE_ID;
import static com.havryliuk.test.users.swager.ErrorResponseConstants.SERVER_ERROR_RESPONSE_ID;
import static com.havryliuk.test.users.swager.ErrorResponseConstants.USER_AGE_RESTRICTION_RESPONSE_ID;
import static com.havryliuk.test.users.swager.ErrorResponseConstants.USER_NOT_FOUND_RESPONSE_ID;
import static com.havryliuk.test.users.swager.ErrorResponseConstants.LONG_PROPERTY_RESPONSE;
import static com.havryliuk.test.users.swager.ErrorResponseConstants.USER_AGE_RESTRICTION_RESPONSE;
import static com.havryliuk.test.users.swager.ErrorResponseConstants.PROPERTY_REQUIRED_RESPONSE;
import static com.havryliuk.test.users.swager.ErrorResponseConstants.NOT_VALID_EMAIL_RESPONSE;
import static com.havryliuk.test.users.swager.ErrorResponseConstants.SERVER_ERROR_RESPONSE;

public interface UserControllerSwaggerDescriptor {
    String TAG_USER = "User operations";
    String REQUIRED = "Required";
    String NOT_VALID = "Not valid";
    String WRONG_SIZE = "Wrong size";
    String INCORRECT_DATA = "Incorrect data";
    String USER_AGE = "User age";
    String USER_NOT_FOUND = "User not found";

    @SuppressWarnings("unused")
    @Tag(name = TAG_USER)
    @Operation(summary = "Creation new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = CREATED, headers = {@Header(name = LOCATION_HEADER,
                    description = USER_CREATED_HEADER_DESCRIPTION)}
            ),
            @ApiResponse(responseCode = "400", description = BAD_REQUEST,
                    content = @Content(schema = @Schema(implementation = GeneralErrorResponse.class),
                            examples = {
                                    @ExampleObject(name = REQUIRED, value = PROPERTY_REQUIRED_RESPONSE),
                                    @ExampleObject(name = NOT_VALID, value = NOT_VALID_EMAIL_RESPONSE),
                                    @ExampleObject(name = WRONG_SIZE, value = LONG_PROPERTY_RESPONSE),
                                    @ExampleObject(name = INCORRECT_DATA, value = INCORRECT_DATA_RESPONSE)
                    })),
            @ApiResponse(responseCode = "403", description = FORBIDDEN,
                    content = @Content(schema = @Schema(implementation = GeneralErrorResponse.class),
                            examples = {@ExampleObject(name = USER_AGE, value = USER_AGE_RESTRICTION_RESPONSE)
                    })),
            @ApiResponse(responseCode = "500", description = SERVER_ERROR,
                    content = @Content(schema = @Schema(implementation = GeneralErrorResponse.class),
                            examples = {@ExampleObject(name = SERVER_ERROR, value = SERVER_ERROR_RESPONSE)
                    }))
    })
    void createUser(@RequestBody DataUserDto user, HttpServletResponse response);


    @SuppressWarnings("unused")
    @Tag(name = TAG_USER)
    @Operation(summary = "Updating user data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = OK),
            @ApiResponse(responseCode = "400", description = BAD_REQUEST,
                    content = @Content(schema = @Schema(implementation = GeneralErrorResponse.class),
                            examples = {
                                    @ExampleObject(name = NOT_VALID, value = NOT_VALID_EMAIL_RESPONSE_ID),
                                    @ExampleObject(name = WRONG_SIZE, value = LONG_PROPERTY_RESPONSE_ID),
                                    @ExampleObject(name = INCORRECT_DATA, value = INCORRECT_DATA_RESPONSE_ID)
                            })),
            @ApiResponse(responseCode = "403", description = FORBIDDEN,
                    content = @Content(schema = @Schema(implementation = GeneralErrorResponse.class),
                            examples = {@ExampleObject(name = USER_AGE, value = USER_AGE_RESTRICTION_RESPONSE_ID)
                            })),
            @ApiResponse(responseCode = "404", description = NOT_FOUND,
                    content = @Content(schema = @Schema(implementation = GeneralErrorResponse.class),
                            examples = @ExampleObject(name = USER_NOT_FOUND, value = USER_NOT_FOUND_RESPONSE_ID))),
            @ApiResponse(responseCode = "500", description = SERVER_ERROR,
                    content = @Content(schema = @Schema(implementation = GeneralErrorResponse.class),
                            examples = {@ExampleObject(name = SERVER_ERROR, value = SERVER_ERROR_RESPONSE_ID)
                            }))
    })
    void updateUserFields(@RequestBody DataUserDto user, @PathVariable String id);



    @SuppressWarnings("unused")
    @Tag(name = TAG_USER)
    @Operation(summary = "Update whole user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = OK),
            @ApiResponse(responseCode = "400", description = BAD_REQUEST,
                    content = @Content(schema = @Schema(implementation = GeneralErrorResponse.class),
                            examples = {
                                    @ExampleObject(name = REQUIRED, value = PROPERTY_REQUIRED_RESPONSE_ID),
                                    @ExampleObject(name = NOT_VALID, value = NOT_VALID_EMAIL_RESPONSE_ID),
                                    @ExampleObject(name = WRONG_SIZE, value = LONG_PROPERTY_RESPONSE_ID),
                                    @ExampleObject(name = INCORRECT_DATA, value = INCORRECT_DATA_RESPONSE_ID)
                            })),
            @ApiResponse(responseCode = "403", description = FORBIDDEN,
                    content = @Content(schema = @Schema(implementation = GeneralErrorResponse.class),
                            examples = {@ExampleObject(name = USER_AGE, value = USER_AGE_RESTRICTION_RESPONSE_ID)
                            })),
            @ApiResponse(responseCode = "404", description = NOT_FOUND,
                    content = @Content(schema = @Schema(implementation = GeneralErrorResponse.class),
                            examples = @ExampleObject(name = USER_NOT_FOUND, value = USER_NOT_FOUND_RESPONSE_ID))),
            @ApiResponse(responseCode = "500", description = SERVER_ERROR,
                    content = @Content(schema = @Schema(implementation = GeneralErrorResponse.class),
                            examples = {@ExampleObject(name = SERVER_ERROR, value = SERVER_ERROR_RESPONSE_ID)
                            }))
    })
    void updateWholeUser(@RequestBody DataUserDto user, @PathVariable String id);






}
