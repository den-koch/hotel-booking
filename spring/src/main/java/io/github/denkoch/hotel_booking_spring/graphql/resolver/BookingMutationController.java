package io.github.denkoch.hotel_booking_spring.graphql.resolver;

import io.github.denkoch.hotel_booking_spring.exceptions.ResourceAlreadyExistsException;
import io.github.denkoch.hotel_booking_spring.exceptions.ResourceNotFoundException;
import io.github.denkoch.hotel_booking_spring.graphql.dto.BookingInput;
import io.github.denkoch.hotel_booking_spring.model.Booking;
import io.github.denkoch.hotel_booking_spring.model.Guest;
import io.github.denkoch.hotel_booking_spring.model.Room;
import io.github.denkoch.hotel_booking_spring.model.RoomId;
import io.github.denkoch.hotel_booking_spring.repository.BookingRepository;
import io.github.denkoch.hotel_booking_spring.repository.GuestRepository;
import io.github.denkoch.hotel_booking_spring.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.util.Collection;

@Controller
@RequiredArgsConstructor
public class BookingMutationController {
    private final BookingRepository bookingRepository;
    private final GuestRepository guestRepository;
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    @MutationMapping
    public Booking createBooking(@Argument(name = "booking") BookingInput bookingInput) {

        Guest guest = guestRepository.findById(bookingInput.getGuestId())
                .orElseThrow(() -> new ResourceNotFoundException("Guest { guestId = " + bookingInput.getGuestId() + "} not found"));

        RoomId roomId = modelMapper.map(bookingInput.getRoomId(), RoomId.class);
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException(
                String.format("Room { roomNumber = %s, roomBuilding = %s } not found",
                        roomId.getRoomNumber(), roomId.getRoomBuilding())));

        Collection<Booking> roomBookings = bookingRepository.findBookedRoomsByIdAndDate(roomId,
                Date.valueOf(bookingInput.getCheckinDate()), Date.valueOf(bookingInput.getCheckoutDate()));
        if (!roomBookings.isEmpty()) {
            throw new ResourceAlreadyExistsException(
                    String.format("Booking on {checkinDate = %s, checkoutDate = %s} already exists",
                            bookingInput.getCheckinDate(), bookingInput.getCheckoutDate()));
        }

        Booking booking = Booking.builder()
                .room(room)
                .checkinDate(Date.valueOf(bookingInput.getCheckinDate()))
                .checkoutDate(Date.valueOf(bookingInput.getCheckoutDate()))
                .guest(guest)
                .build();

        return bookingRepository.save(booking);
    }

    @MutationMapping
    public Booking updateBooking(@Argument Long bookingId, @Argument(name = "booking") BookingInput bookingInput) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking { bookingId = " + bookingId + "} not found"));

//        RoomId roomId = modelMapper.map(bookingInput.getRoomId(), RoomId.class);
        Date checkinDate = Date.valueOf(bookingInput.getCheckinDate());
        Date checkoutDate = Date.valueOf(bookingInput.getCheckoutDate());

        Collection<Booking> roomBookings =
                bookingRepository.findBookedRoomsByIdAndDate(booking.getRoom().getRoomId(),
                        checkinDate, checkoutDate);
        roomBookings.remove(booking);

        if (roomBookings.size() > 1) {
            throw new ResourceAlreadyExistsException(
                    String.format("Booking on {checkinDate = %s, checkoutDate = %s} already exists",
                            checkinDate, checkoutDate));
        }

        booking.setCheckinDate(checkinDate);
        booking.setCheckoutDate(checkoutDate);

        return bookingRepository.save(booking);

    }

    @MutationMapping
    public String deleteBookingById(@Argument Long bookingId) {
        bookingRepository.deleteById(bookingId);
        return "Booking { bookingId = " + bookingId + "} deleted";
    }

}
