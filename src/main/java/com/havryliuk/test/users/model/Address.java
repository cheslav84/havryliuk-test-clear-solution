package com.havryliuk.test.users.model;

import com.havryliuk.test.users.valitator.groups.FieldsRequired;
import com.havryliuk.test.users.valitator.groups.OptionalFields;
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
    @Size(max = 32, message = "Country should be maximum 32 characters long",
            groups = {FieldsRequired.class, OptionalFields.class})
    @Size(max = 32, message = "Country should be maximum 32 characters long")
    @Schema(example = COUNTRY)
    private String country;
    @Size(max = 32, message = "City should be maximum 32 characters long",
            groups = {FieldsRequired.class, OptionalFields.class})
//    @Size(max = 32, message = "City should be maximum 32 characters long")
    @Schema(example = CITY)
    private String city;
    @Size(max = 32, message = "Street should be maximum 32 characters long",
            groups = {FieldsRequired.class, OptionalFields.class})
//    @Size(max = 32, message = "Street should be maximum 32 characters long")
    @Schema(example = STREET)
    private String street;
    @Size(min = 5, max = 10, message = "Zipcode should be from 5 to 10 characters long",
            groups = {FieldsRequired.class, OptionalFields.class})
//    @Size(min = 5, max = 10, message = "Zipcode should be from 5 to 10 characters long")
    @Schema(example = ZEEP_CODE)
    private String zipcode;

}
