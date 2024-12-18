package io.github.denkoch.hotel_booking_spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GuestResponseDTO {

    @NotNull
    private Long guestId;

    @NotBlank
    @Schema(example = "Denys")
    private String firstName;

    @NotBlank
    @Schema(example = "Kochetov")
    private String lastName;

    @Email
    @Schema(example = "qwerty@example.com")
    private String email;

    @Schema(example = "+1234567890")
    private String phoneNumber;
}