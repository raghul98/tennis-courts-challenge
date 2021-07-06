package com.tenniscourts.reservations;

import com.tenniscourts.config.BaseRestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/reservations")
public class ReservationController extends BaseRestController {
	
	@Autowired	
    private final ReservationService reservationService;
	
	@PostMapping
	@ApiOperation(value="Books a reservation",notes="Books a new reservation by getting requestDTO object")
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Successfully booked a reservation")})
    public ResponseEntity<Void> bookReservation(CreateReservationRequestDTO createReservationRequestDTO) {
        return ResponseEntity.created(locationByEntity(reservationService.bookReservation(createReservationRequestDTO).getId())).build();
    }
	
	@GetMapping("/{id}")
	@ApiOperation(value="Finds a reservation",notes="Finds an existing reservation by getting the reservation id")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Search Successfull")})
    public ResponseEntity<ReservationDTO> findReservation(@PathVariable Long reservationId) {
        return ResponseEntity.ok(reservationService.findReservation(reservationId));
    }
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Cancels a reservation",notes="Cancels an old reservation by getting the reservation id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Reservation cancelled Successfully")})
    public ResponseEntity<ReservationDTO> cancelReservation(@PathVariable Long reservationId) {
        return ResponseEntity.ok(reservationService.cancelReservation(reservationId));
    }
	
	@PatchMapping("/{id}")
	@ApiOperation(value="Reschedules a reservation",notes="Reschedules an old reservation by getting a reservation id and schedule id")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Reschedule Successfull")})
    public ResponseEntity<ReservationDTO> rescheduleReservation(@PathVariable Long reservationId,@RequestParam Long scheduleId) {
        return ResponseEntity.ok(reservationService.rescheduleReservation(reservationId, scheduleId));
    }
}
