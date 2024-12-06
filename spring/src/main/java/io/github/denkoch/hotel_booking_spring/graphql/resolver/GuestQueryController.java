package io.github.denkoch.hotel_booking_spring.graphql.resolver;

import io.github.denkoch.hotel_booking_spring.exceptions.ResourceNotFoundException;
import io.github.denkoch.hotel_booking_spring.model.Guest;
import io.github.denkoch.hotel_booking_spring.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class GuestQueryController {

    private final GuestRepository guestRepository;

    @QueryMapping
    public Iterable<Guest> getGuests() {
        return guestRepository.findAll();
    }

    @QueryMapping
    public Guest getGuestById(@Argument Long guestId) {
        return guestRepository.findById(guestId).orElseThrow(ResourceNotFoundException::new);
    }

}
