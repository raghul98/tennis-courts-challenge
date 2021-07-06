package com.tenniscourts.guests;

import com.tenniscourts.config.BaseRestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/guests")
public class GuestController extends BaseRestController {
	
	@Autowired
    private final GuestService guestService;

    @PostMapping
    @ApiOperation(value="Adds a Guest",notes="Adds a new Guest by getting the GuestDTO object")
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Successfully added a Guest")})
    public ResponseEntity<Void> addGuest(@RequestBody GuestDTO GuestDTO) {
        return ResponseEntity.created(locationByEntity(guestService.addGuest(GuestDTO).getId())).build();
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value="Finds a Guest by ID",notes="Finds a Guest by getting the Guest id")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Search Successfull")})
    public ResponseEntity<GuestDTO> findGuestById(@PathVariable Long GuestId) {
        return ResponseEntity.ok(guestService.findGuestById(GuestId));
    }
    
    @GetMapping("/{name}")
    @ApiOperation(value="Finds a Guest by name",notes="Finds a Guest by name by getting the guest name")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Search Successfull")})
    public ResponseEntity<List<GuestDTO>> findGuestByName(@PathVariable String name) {
        return ResponseEntity.ok(guestService.findGuestByName(name));
    }
    
    @GetMapping("/")
    @ApiOperation(value="Finds all Guests",notes="Finds all the available Guests")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Search Successfull")})
    public ResponseEntity<List<GuestDTO>> findGuests() {
        return ResponseEntity.ok(guestService.findGuests());
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation(value="Finds all Guests",notes="Delete the Guest by getting the id")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Deletion Successfull")})
    public ResponseEntity<GuestDTO> deleteGuest( @PathVariable Long guestId) {
    	return ResponseEntity.ok(guestService.deleteGuest(guestId));
    }
    
    @PutMapping("/{id}")
    @ApiOperation(value="Updates a Guest",notes="Updates the available Guest")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Update Successfull")})
    public ResponseEntity<GuestDTO> updateGuest( @PathVariable Long guestId) {
    	return ResponseEntity.ok(guestService.updateGuest(guestId));
    }
    

}
