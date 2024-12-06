package io.github.denkoch.hotel_booking_spring.graphql.dto;

import lombok.Data;

@Data
public class BookingInput {
    private RoomIdInput roomId;
    private String checkinDate;
    private String checkoutDate;
    private Long guestId;
}
