package io.github.denkoch.hotel_booking_spring.service;

import io.github.denkoch.hotel_booking_spring.dto.GuestDTO;
import io.github.denkoch.hotel_booking_spring.model.Guest;
import io.github.denkoch.hotel_booking_spring.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;
    private final ModelMapper modelMapper;

    public Optional<Guest> getGuestByEmail(GuestDTO guestDTO) {
        return guestRepository.findByEmail(guestDTO.getEmail());
    }

    @Transactional
    public Guest createGuest(GuestDTO guestDTO) {
        Guest guest = modelMapper.map(guestDTO, Guest.class);

        guest = guestRepository.save(guest);

        return guest;
    }


}
