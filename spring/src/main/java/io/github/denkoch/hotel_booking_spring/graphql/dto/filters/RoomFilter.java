package io.github.denkoch.hotel_booking_spring.graphql.dto.filters;

import lombok.Data;

@Data
public class RoomFilter {
    private FilterField roomCapacity;
    private FilterField roomPrice;
    private FilterField roomType;
}
