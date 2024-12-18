package io.github.denkoch.hotel_booking_spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class RoomTypeDTO {

    @NotBlank
    private String roomTypeId;

    @NotBlank
    @Schema(example = "Deluxe")
    private String roomType;

    @PositiveOrZero
    @Schema(example = "123")
    private Long costPerDay;
}
