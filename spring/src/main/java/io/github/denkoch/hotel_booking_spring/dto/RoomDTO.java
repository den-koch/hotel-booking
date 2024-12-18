package io.github.denkoch.hotel_booking_spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class RoomDTO {

    @NotNull
    private RoomIdDTO roomId;

    private RoomTypeDTO roomType;

    @Positive
    @Schema(example = "3")
    private Long capacity;

    @PositiveOrZero
    @Schema(example = "123")
    private Long price;

}
