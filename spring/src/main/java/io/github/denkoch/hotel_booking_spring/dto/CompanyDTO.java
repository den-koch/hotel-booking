package io.github.denkoch.hotel_booking_spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompanyDTO {

    @NotBlank
    @Schema(example = "Global Corp.")
    private String companyName;

    @Email
    @Schema(example = "info@globalcorp.com")
    private String companyEmail;

    @Schema(example = "+1234567890")
    private String companyPhoneNumber;
}
