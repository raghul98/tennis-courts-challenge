package com.tenniscourts.tenniscourts;

import com.tenniscourts.config.BaseRestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@AllArgsConstructor
@RequestMapping("/tenniscourts")
public class TennisCourtController extends BaseRestController {
	
	@Autowired
    private final TennisCourtService tennisCourtService;

    @PostMapping
    @ApiOperation(value="Adds a Tennis Court",notes="Adds a new tennis court by getting the tenniscourtDTO object")
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Successfully added a Tennis Court")})
    public ResponseEntity<Void> addTennisCourt(@RequestBody TennisCourtDTO tennisCourtDTO) {
        return ResponseEntity.created(locationByEntity(tennisCourtService.addTennisCourt(tennisCourtDTO).getId())).build();
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value="Finds Tennis Court",notes="Finds a Tennis Court by getting the tennis court id")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Search Successfull")})
    public ResponseEntity<TennisCourtDTO> findTennisCourtById(@PathVariable Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtById(tennisCourtId));
    }
    
    @GetMapping("/{id}/schedules")
    @ApiOperation(value="Finds Tennis Courts",notes="Finds Tennis Courts with Schedules by getting the tennis court id")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Search Successfull")})
    public ResponseEntity<TennisCourtDTO> findTennisCourtWithSchedulesById(@PathVariable Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtWithSchedulesById(tennisCourtId));
    }
}
