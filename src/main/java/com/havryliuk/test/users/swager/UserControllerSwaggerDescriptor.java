package com.havryliuk.test.users.swager;
import com.havryliuk.test.users.dto.request.UserCreationDto;
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
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

import static com.havryliuk.test.users.swager.ErrorResponseConstants.INCORRECT_DATA;
import static com.havryliuk.test.users.util.GlobalConstants.USER_CREATED_HEADER_DESCRIPTION;
import static com.havryliuk.test.users.swager.ErrorResponseConstants.LONG_PROPERTY;
import static com.havryliuk.test.users.swager.ErrorResponseConstants.USER_AGE_RESTRICTION;
import static com.havryliuk.test.users.swager.ErrorResponseConstants.PROPERTY_REQUIRED;
import static com.havryliuk.test.users.swager.ErrorResponseConstants.NOT_VALID_EMAIL;
import static com.havryliuk.test.users.swager.ErrorResponseConstants.SERVER_ERROR;

public interface UserControllerSwaggerDescriptor {

    @SuppressWarnings("unused")
    @Tag(name = "Creating user")
    @Operation(summary = "Create new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", headers = {@Header(name = "Location",
                    description = USER_CREATED_HEADER_DESCRIPTION)}
            ),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(schema = @Schema(implementation = GeneralErrorResponse.class),
                            examples = {
                                    @ExampleObject(name = "Required", value = PROPERTY_REQUIRED),
                                    @ExampleObject(name = "Not valid", value = NOT_VALID_EMAIL),
                                    @ExampleObject(name = "Wrong size", value = LONG_PROPERTY),
                                    @ExampleObject(name = "Incorrect data", value = INCORRECT_DATA)
                    })),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = GeneralErrorResponse.class),
                            examples = {@ExampleObject(name = "User age", value = USER_AGE_RESTRICTION)
                    })),
            @ApiResponse(responseCode = "500", description = "Server Error",
                    content = @Content(schema = @Schema(implementation = GeneralErrorResponse.class),
                            examples = {@ExampleObject(name = "Server error", value = SERVER_ERROR)
                    }))
    })
    void createUser(@Valid @RequestBody UserCreationDto user, HttpServletResponse response);
}
