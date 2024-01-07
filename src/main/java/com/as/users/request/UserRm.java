package com.as.users.request;

import com.as.users.enums.DateTimeType;
import com.as.users.validator.LocalDateTimeFormat;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.CreditCardNumber;

public record UserRm(
        String username,
        @Email
        String email,
        @Size(min=2)
        String firstName,
        @NotNull
        @NotBlank
        String lastName,
        @Min(18)
        @Max(36)
        int age,
        @CreditCardNumber
        String creditCardNumber,
        @LocalDateTimeFormat(dateTimeType = DateTimeType.DateTime, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
        String registrationDate
) {

}
