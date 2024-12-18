package io.github.denkoch.hotel_booking_spring.controller;

import io.github.denkoch.hotel_booking_spring.dto.HistoryDTO;
import io.github.denkoch.hotel_booking_spring.service.BookingService;
import io.github.denkoch.hotel_booking_spring.service.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/history")
@Tag(name = "history-controller", description = "Operations with Hotel History")
public class HistoryController {

    private final HistoryService historyService;
    private final ModelMapper modelMapper;

    @Operation(summary = "Get list of hotel history records",
            description = "This method returns list of hotel history records"
    )
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success")})
    @GetMapping
    public ResponseEntity<List<HistoryDTO>> getHistory() {
        List<HistoryDTO> history = historyService.getHistory();
        return ResponseEntity.ok(history);
    }

}
