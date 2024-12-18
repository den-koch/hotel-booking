package io.github.denkoch.hotel_booking_spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class RoomBuildingsDTO {

    @NotBlank
    @Schema(example = "B")
    private String roomBuilding;

    @NotEmpty
    @Schema(example = "[101, 102]")
    private List<Long> roomNumbers;
}
