package com.havryliuk.test.users.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.havryliuk.test.users.util.GlobalConstants.CITY;
import static com.havryliuk.test.users.util.GlobalConstants.COUNTRY;
import static com.havryliuk.test.users.util.GlobalConstants.STREET;
import static com.havryliuk.test.users.util.GlobalConstants.ZEEP_CODE;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
    @Schema(example = COUNTRY)
    @Size(max = 32, message = "Country should be maximum 32 characters long")
    private String country;
    @Schema(example = CITY)
    @Size(max = 32, message = "City should be maximum 32 characters long")
    private String city;
    @Schema(example = STREET)
    @Size(max = 32, message = "Street should be maximum 32 characters long")
    private String street;
    @Schema(example = ZEEP_CODE)
    @Size(min = 5, max = 10, message = "Zipcode should be from 5 to 10 characters long")
    private String zipcode;

}
