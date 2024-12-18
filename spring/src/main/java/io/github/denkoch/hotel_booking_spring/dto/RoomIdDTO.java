package io.github.denkoch.hotel_booking_spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoomIdDTO {

    @NotNull
    @Schema(example = "101")
    private Long roomNumber;

    @NotBlank
    @Schema(example = "B")
    private String roomBuilding;

}
