package com.havryliuk.test.users.dto.response;

import com.havryliuk.test.users.swager.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.With;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.Instant;

@With
@Builder
public record GeneralErrorResponse (
                @Schema(example = Constants.MESSAGE_TIME)
                Instant timestamp,

                @Schema(example = Constants.STATUS)
                int statusCode,

                @Schema(example = Constants.ERROR)
                String error,

                @Schema(example = Constants.SERVER_ERROR_MESSAGE)
                String message,

                @Schema(example = Constants.PATH)
                String path
                ) {
}
//{
//        "timestamp": "2019-01-17T16:12:45.977+0000",
//        "statusCode": 500,
//        "error": "Internal Server Error",
//        "message": "Error processing the request!",
//        "path": "/my-endpoint-with-exceptions"
//        }