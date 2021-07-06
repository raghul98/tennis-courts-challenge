package com.tenniscourts.schedules;

import com.tenniscourts.config.BaseRestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController extends BaseRestController {
	
	@Autowired
    private final ScheduleService scheduleService;

	@PostMapping
	@ApiOperation(value="Adds a Schedule",notes="Adds a new Schedule to a tennis court by getting the schedule requestDTO object")
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Successfully added a Schedule")})
    public ResponseEntity<Void> addScheduleTennisCourt(CreateScheduleRequestDTO createScheduleRequestDTO) {
        return ResponseEntity.created(locationByEntity(scheduleService.addSchedule(createScheduleRequestDTO.getTennisCourtId(), createScheduleRequestDTO).getId())).build();
    }

	@GetMapping
    @ApiOperation(value="Finds Schedules by Dates",notes="Finds available Schedules between the start date and end date")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Search Successfull")})
    public ResponseEntity<List<ScheduleDTO>> findSchedulesByDates(LocalDate startDate,
                                                                  LocalDate endDate) {
        return ResponseEntity.ok(scheduleService.findSchedulesByDates(LocalDateTime.of(startDate, LocalTime.of(0, 0)), LocalDateTime.of(endDate, LocalTime.of(23, 59))));
    }

	@GetMapping("/{id}")
    @ApiOperation(value="Finds Schedules by ID",notes="Finds a Schedule by getting the Schedule id")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Search Successfull")})
    public ResponseEntity<ScheduleDTO> findByScheduleId(Long scheduleId) {
        return ResponseEntity.ok(scheduleService.findSchedule(scheduleId));
    }
	
    @ApiOperation(value="Finds all Schedules",notes="Finds all Schedules available")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Search Successfull")})
    @GetMapping("/all")
    public ResponseEntity<List<ScheduleDTO>> findAvailableSchedules() {

        return ResponseEntity.ok(scheduleService.findAllSchedules());
    }
//	@GetMapping("/{id}")
 //   public ResponseEntity<List<ScheduleDTO>> findSchedulesByTennisCourtId(Long tennisCourtId){
//		return ResponseEntity.ok(scheduleService.findSchedulesByTennisCourtId(tennisCourtId));
//	}
}
