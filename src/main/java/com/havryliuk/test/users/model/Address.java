package com.havryliuk.test.users.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.havryliuk.test.users.valitator.groups.FieldsRequired;
import com.havryliuk.test.users.valitator.groups.OptionalFields;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.havryliuk.test.users.util.GlobalConstants.CITY;
import static com.havryliuk.test.users.util.GlobalConstants.COUNTRY;
import static com.havryliuk.test.users.util.GlobalConstants.LONG_PROPERTY_32;
import static com.havryliuk.test.users.util.GlobalConstants.PROPERTY_SIZE_5_TO_10;
import static com.havryliuk.test.users.util.GlobalConstants.STREET;
import static com.havryliuk.test.users.util.GlobalConstants.ZEEP_CODE;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Address {
    @Size(max = 32, message = LONG_PROPERTY_32, groups = {FieldsRequired.class, OptionalFields.class})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(example = COUNTRY)
    private String country;

    @Size(max = 32, message = LONG_PROPERTY_32, groups = {FieldsRequired.class, OptionalFields.class})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(example = CITY)
    private String city;

    @Size(max = 32, message = LONG_PROPERTY_32, groups = {FieldsRequired.class, OptionalFields.class})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(example = STREET)
    private String street;

    @Size(min = 5, max = 10, message = PROPERTY_SIZE_5_TO_10,  groups = {FieldsRequired.class, OptionalFields.class})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(example = ZEEP_CODE)
    private String zipcode;
}