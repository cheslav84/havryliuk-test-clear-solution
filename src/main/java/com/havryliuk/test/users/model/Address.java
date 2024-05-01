package com.havryliuk.test.users.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Embeddable
public class Address {
    @Schema(example = "Ukraine")
    @Size(max = 32, message = "Country should be maximum 32 characters long")
    private String country;
    @Schema(example = "Kyiv")
    @Size(max = 32, message = "City should be maximum 32 characters long")
    private String city;
    @Schema(example = "Volodymyrska")
    @Size(max = 32, message = "Street should be maximum 32 characters long")
    private String street;
    @Schema(example = "03455")
    @Size(min = 5, max = 10, message = "Zipcode should be from 5 to 10 characters long")
    private String zipcode;

}
