package io.github.denkoch.hotel_booking_spring.graphql.resolver;

import io.github.denkoch.hotel_booking_spring.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class RoomQueryController {
    private final RoomRepository roomRepository;
}
