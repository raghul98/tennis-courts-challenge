package com.tenniscourts.schedules;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniscourts.exceptions.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleService {
	@Autowired
    private final ScheduleRepository scheduleRepository;

    @Autowired
    private final ScheduleMapper scheduleMapper;


    public ScheduleDTO addSchedule(Long tennisCourtId, CreateScheduleRequestDTO createScheduleRequestDTO) {
        //TODO: implement addSchedule
    	return scheduleMapper.map(scheduleRepository.saveAndFlush(scheduleMapper.map(createScheduleRequestDTO)));
//        return null;
    }

    public List<ScheduleDTO> findSchedulesByDates(LocalDateTime startDate, LocalDateTime endDate) {
        //TODO: implement
    	return scheduleMapper.map(scheduleRepository.findByStartDateGreaterThanEqualAndEndDateLessThanEqual(startDate,endDate));
    }

    public ScheduleDTO findSchedule(Long scheduleId) {
        //TODO: implement
        //return null;
    	//implemented using findreservation method from reservationservice
    	return scheduleRepository.findById(scheduleId).map(scheduleMapper::map).orElseThrow(() -> {
            throw new EntityNotFoundException("Schedule not found.");
        });
    }

    public List<ScheduleDTO> findSchedulesByTennisCourtId(Long tennisCourtId) {
        return scheduleMapper.map(scheduleRepository.findByTennisCourt_IdOrderByStartDateTime(tennisCourtId));
    }
    public List<ScheduleDTO> findAllSchedules() {

        return scheduleMapper.map(scheduleRepository.findAll());
    }
}
