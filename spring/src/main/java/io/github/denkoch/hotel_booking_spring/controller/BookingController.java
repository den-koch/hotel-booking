package io.github.denkoch.hotel_booking_spring.controller;

import io.github.denkoch.hotel_booking_spring.dto.BookingCreationDTO;
import io.github.denkoch.hotel_booking_spring.dto.BookingDetailsDTO;
import io.github.denkoch.hotel_booking_spring.dto.BookingSummaryDTO;
import io.github.denkoch.hotel_booking_spring.model.Booking;
import io.github.denkoch.hotel_booking_spring.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
@Tag(name = "booking-controller", description = "Operations with Hotel reservations")
public class BookingController {

    private final BookingService bookingService;
    private final ModelMapper modelMapper;

    @Operation(summary = "Get list of hotel booking by building",
            description = "This method returns list of hotel booking by building Id",
            parameters = {
                    @Parameter(name = "buildingId", description = "Building identifier", example = "B"),
            }
    )
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success")})
    @GetMapping
    public ResponseEntity<List<BookingSummaryDTO>> getBookingByBuilding(@RequestParam String buildingId) {
        List<BookingSummaryDTO> bookings = bookingService.getBookingByBuilding(buildingId);
        return ResponseEntity.ok(bookings);
    }

    @Operation(summary = "Get hotel booking by Id",
            description = "This method returns specific hotel booking by Id",
            parameters = {
                    @Parameter(name = "bookingId", description = "Booking identifier", example = "123")
            }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BookingDetailsDTO.class))}),
            @ApiResponse(responseCode = "404", description = "NotFound", content = @Content)}
    )
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDetailsDTO> getBookingDetail(@PathVariable Long bookingId) {
        Optional<BookingDetailsDTO> booking = bookingService.getBookingDetail(bookingId);
        return booking.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create hotel booking",
            description = "This method creates a new hotel room reservation",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Booking Creation Entity", required = true,
                    content = @Content(schema = @Schema(implementation = BookingCreationDTO.class))
            ))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BookingDetailsDTO.class))}),
            @ApiResponse(responseCode = "400", description = "BadRequest", content = @Content)}
    )
    @PostMapping
    public ResponseEntity<BookingDetailsDTO> postBooking(@RequestBody @Valid BookingCreationDTO bookingCreationDTO) {

        Booking booking = bookingService.createBooking(bookingCreationDTO);

        BookingDetailsDTO bookingDetails = modelMapper.map(booking, BookingDetailsDTO.class);

        if (bookingDetails == null) {
            return ResponseEntity.badRequest().build();
        }

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{bookingId}")
                .buildAndExpand(bookingDetails.getBookingId()).toUri();

        return ResponseEntity.created(uri).body(bookingDetails);
    }

    @Operation(summary = "Update hotel booking reservation info",
            description = "This method updates a hotel room reservation info",
            parameters = {
                    @Parameter(name = "bookingId", description = "Booking identifier", example = "123"),
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Booking Creation Entity", required = true,
                    content = @Content(schema = @Schema(implementation = BookingCreationDTO.class))
            ))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BookingDetailsDTO.class))}),
            @ApiResponse(responseCode = "400", description = "BadRequest", content = @Content),
            @ApiResponse(responseCode = "404", description = "NotFound", content = @Content)}
    )
    @PutMapping("/{bookingId}")
    public ResponseEntity<BookingDetailsDTO> putBooking(@PathVariable Long bookingId,
                                                        @RequestBody @Valid BookingCreationDTO bookingCreationDTO) {
        Booking booking = bookingService.updateBooking(bookingId, bookingCreationDTO);
        BookingDetailsDTO bookingDetailsDTO = modelMapper.map(booking, BookingDetailsDTO.class);
        return ResponseEntity.ok(bookingDetailsDTO);
    }

    @Operation(summary = "Delete hotel booking reservation by Id",
            description = "This method deletes a hotel booking reservation by Id",
            parameters = {
                    @Parameter(name = "bookingId", description = "Booking identifier", example = "123")
            }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "NoContent"),
            @ApiResponse(responseCode = "404", description = "NotFound", content = @Content)}
    )
    @DeleteMapping("/{bookingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);
    }

//    @GetMapping
//    public ResponseEntity<List<BookingSummaryDTO>> getBookings() {
//        List<BookingSummaryDTO> bookings = bookingService.getAllBookings();
//        return ResponseEntity.ok(bookings);
//    }

}
