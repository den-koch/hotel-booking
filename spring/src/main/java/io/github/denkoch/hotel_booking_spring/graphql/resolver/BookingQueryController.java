package io.github.denkoch.hotel_booking_spring.graphql.resolver;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import io.github.denkoch.hotel_booking_spring.exceptions.ResourceNotFoundException;
import io.github.denkoch.hotel_booking_spring.model.Booking;
import io.github.denkoch.hotel_booking_spring.model.Guest;
import io.github.denkoch.hotel_booking_spring.model.Room;
import io.github.denkoch.hotel_booking_spring.model.RoomType;
import io.github.denkoch.hotel_booking_spring.repository.BookingRepository;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class BookingQueryController {

    private final BookingRepository bookingRepository;

    @QueryMapping
    public Iterable<Booking> getBookings(DataFetchingEnvironment env) {
        Specification<Booking> spec = Specification.where(null);
        spec = dataFetching(spec, env);
        return bookingRepository.findAll(spec);
    }

    @QueryMapping
    public Booking getBookingById(@Argument Long bookingId, DataFetchingEnvironment env) {
        Specification<Booking> spec = byBookingId(bookingId);
        spec = dataFetching(spec, env);
        return bookingRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException(""));
    }

    @QueryMapping
    Iterable<Booking> getBookingsByBuilding(@Argument String roomBuilding,
                                            DataFetchingEnvironment env) {
        Specification<Booking> spec = byRoomBuilding(roomBuilding);
        spec = dataFetching(spec, env);
        return bookingRepository.findAll(spec);
    }

    private Specification<Booking> dataFetching(Specification<Booking> spec,
                                                DataFetchingEnvironment env){
        DataFetchingFieldSelectionSet selectionSet = env.getSelectionSet();

        if (selectionSet.contains("room")) {
            spec = spec.and(fetchRoom());
        }
        if (selectionSet.contains("guest"))
            spec = spec.and(fetchGuest());

        return spec;
    }

    private Specification<Booking> fetchRoom() {
        return (root, query, builder) -> {
            Fetch<Booking, Room> f = root.fetch("room", JoinType.LEFT);
            f.fetch("roomType", JoinType.LEFT);
            Join<Booking, Room> join = (Join<Booking, Room>) f;
            return join.getOn();
        };
    }

    private Specification<Booking> fetchGuest() {
        return (root, query, builder) -> {
            Fetch<Booking, Guest> f = root
                    .fetch("guest", JoinType.LEFT);
            Join<Booking, Guest> join = (Join<Booking, Guest>) f;
            return join.getOn();
        };
    }

    private Specification<Booking> byBookingId(Long bookingId) {
        return (root, query, builder) -> builder.equal(root.get("bookingId"), bookingId);
    }

    private Specification<Booking> byRoomBuilding(String roomBuilding) {
        return (root, query, builder) -> builder.equal(root.get("room").get("roomId").get("roomBuilding"), roomBuilding);
    }

}
