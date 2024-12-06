package io.github.denkoch.hotel_booking_spring.graphql.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoomIdInput {
    private Long roomNumber;
    private String roomBuilding;
}
