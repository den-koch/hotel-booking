package io.github.denkoch.hotel_booking_spring.controller;

import io.github.denkoch.hotel_booking_spring.dto.RoomBuildingsDTO;
import io.github.denkoch.hotel_booking_spring.dto.RoomDTO;
import io.github.denkoch.hotel_booking_spring.dto.RoomTypeDTO;
import io.github.denkoch.hotel_booking_spring.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "room-controller", description = "Operations with Hotel Rooms")
public class RoomController {

    private final RoomService roomService;

    @Operation(summary = "Get list of hotel rooms by buildings",
            description = "This method returns list of hotel rooms by buildings"
    )
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success")})
    @GetMapping("/home")
    public ResponseEntity<List<RoomBuildingsDTO>> getBuildingRooms() {
        List<RoomBuildingsDTO> roomsBuildings = roomService.getBuildingRooms();
        return ResponseEntity.ok(roomsBuildings);
    }

    @Operation(summary = "Get list of hotel rooms by building and filter",
            description = "This method returns list hotel rooms by building and filter",
            parameters = {
                    @Parameter(name = "buildingId", description = "Building identifier", example = "B"),
                    @Parameter(name = "roomCapacity", description = " Room capacity filter", example = "3"),
                    @Parameter(name = "roomPrice", description = " Room price filter", example = "123"),
                    @Parameter(name = "roomType", description = " Room type filter", example = "Deluxe")
            }
    )
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success")})
    @GetMapping("/rooms")
    public ResponseEntity<List<RoomDTO>> getRoomTypes(@RequestParam String buildingId,
                                                      @RequestParam(required = false) Long roomCapacity,
                                                      @RequestParam(required = false) Long roomPrice,
                                                      @RequestParam(required = false) String roomType) {
        List<RoomDTO> rooms = roomService.getRoomsByBuildingAndFilters(buildingId, roomCapacity, roomPrice, roomType);
        return ResponseEntity.ok(rooms);
    }

}
