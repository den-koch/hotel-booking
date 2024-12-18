package io.github.denkoch.hotel_booking_spring.dto;

import io.github.denkoch.hotel_booking_spring.config.validator.FutureOrPresentSqlDate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;

@Data
public class BookingCreationDTO {

    @NotNull
    private RoomDTO room;

    @FutureOrPresentSqlDate
    @Schema(example = "2024-12-15", type = "string")
    private Date checkinDate;

    @FutureOrPresent
    @Schema(example = "2024-12-18", type = "string")
    private Date checkoutDate;

    @NotNull
    private GuestDTO guest;

    private CompanyDTO company;
}
