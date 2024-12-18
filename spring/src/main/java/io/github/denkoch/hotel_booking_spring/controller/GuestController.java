package io.github.denkoch.hotel_booking_spring.controller;

import io.github.denkoch.hotel_booking_spring.dto.BookingDetailsDTO;
import io.github.denkoch.hotel_booking_spring.dto.GuestResponseDTO;
import io.github.denkoch.hotel_booking_spring.service.GuestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guests")
@Tag(name = "guests-controller", description = "Operations with Hotel Guests")
public class GuestController {

    private final GuestService guestService;

    @Operation(summary = "Get list of guests by Ids",
            description = "This method returns list of guests by guests Ids",
            parameters = {
                    @Parameter(name = "ids", description = "Guests identifiers", example = "1,2,3"),
            }
    )
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success")})
    @GetMapping
    public ResponseEntity<List<GuestResponseDTO>> getGuestsByIds(@RequestParam(name = "ids") List<Long> ids) {
        List<GuestResponseDTO> guestDTOs = guestService.getGuestsByIds(ids);
        return ResponseEntity.ok(guestDTOs);
    }

    @Operation(summary = "Get guest info by Id",
            description = "This method returns specific guest info by Id",
            parameters = {
                    @Parameter(name = "guestId", description = "Guest identifier", example = "1")
            }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GuestResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "NotFound", content = @Content)}
    )
    @GetMapping("/{guestId}")
    public ResponseEntity<GuestResponseDTO> getGuestsById(@PathVariable(name = "guestId") Long guestId) {
        GuestResponseDTO guest = guestService.getGuestsById(guestId);
        return ResponseEntity.ok(guest);
    }

}
