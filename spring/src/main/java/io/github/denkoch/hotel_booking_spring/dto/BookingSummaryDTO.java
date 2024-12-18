package io.github.denkoch.hotel_booking_spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;

@Data
public class BookingSummaryDTO {

    @NotNull
    private Long bookingId;

    @NotNull
    private RoomIdDTO roomId;

    @NotNull
    @Schema(example = "2024-12-15", type = "string")
    private Date checkinDate;

    @FutureOrPresent
    @Schema(example = "2024-12-15", type = "string")
    private Date checkoutDate;
}
