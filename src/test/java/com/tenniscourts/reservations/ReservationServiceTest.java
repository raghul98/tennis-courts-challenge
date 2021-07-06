package com.tenniscourts.reservations;

import static java.util.stream.IntStream.range;
import java.util.*;
import org.apache.commons.*;
//import static org.apache.commons.lang3.RandomUtils.nextInt;
//import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.guests.Guest;
import com.tenniscourts.guests.GuestService;
import com.tenniscourts.schedules.ScheduleDTO;
import com.tenniscourts.schedules.ScheduleMapper;
import com.tenniscourts.schedules.ScheduleService;
import com.tenniscourts.tenniscourts.TennisCourt;
import com.tenniscourts.tenniscourts.TennisCourtDTO;

import java.util.Scanner.*;
import com.tenniscourts.schedules.Schedule;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ReservationService.class)
public class ReservationServiceTest {
	@InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ReservationMapper reservationMapper;

    @Mock
    private ScheduleService scheduleService;

    @Mock
    private GuestService guestService;
    private TennisCourt tenniscourt;
    private ReservationDTO reservationDTO;
    private Reservation reservation;
    
    private ScheduleDTO scheduleDTO;
    private Schedule schedule;

    private Long id;
    private TennisCourtDTO tenniscourtDTO;
    
    private CreateReservationRequestDTO createReservationRequestDTO;
    private List<ScheduleDTO> schedulesDTO;
    private Long scheduleId;

    @Before
    public void before() {
    	Random rd = new Random();
        id = rd.nextLong();
        scheduleId = rd.nextLong();
        tenniscourtDTO = new TennisCourtDTO();
        tenniscourtDTO.setId(rd.nextLong());
        tenniscourtDTO.setName("Ralph lauren");
        
//  
//        tenniscourt = tenniscourtMapper.map(tenniscourtDTO)
        scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(rd.nextLong());
        scheduleDTO.setTennisCourt(tenniscourtDTO);
        scheduleDTO.setTennisCourtId(rd.nextLong());
        tenniscourt = new TennisCourt();
        tenniscourt.setId(tenniscourtDTO.getId());
        tenniscourt.setName(tenniscourtDTO.getName());
        schedule = new Schedule();
        schedule.setId(scheduleDTO.getId());
        schedule.setTennisCourt(tenniscourt);

        
        reservationDTO = new ReservationDTO();
        reservationDTO.setId(rd.nextLong());
        reservationDTO.setSchedule(scheduleDTO);
        reservationDTO.setGuestId(rd.nextLong());
        reservationDTO.setRefundValue(BigDecimal.ZERO);
        reservationDTO.setValue(BigDecimal.valueOf(10L));
        reservationDTO.setGuestId(rd.nextLong());
        reservation = new Reservation();
        reservation.setId(id);
        reservation.setSchedule(schedule);
        reservation.getSchedule().setId(rd.nextLong());
        reservation.setGuest(new Guest() {{setId(rd.nextLong());
        }});
        
        schedulesDTO = range(0, rd.nextInt(5))
            .mapToObj(i -> new ScheduleDTO())
            .collect(Collectors.toList());
        
        createReservationRequestDTO = new CreateReservationRequestDTO();
        createReservationRequestDTO.setGuestId(rd.nextLong());
        createReservationRequestDTO.setScheduleId(rd.nextLong());
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(createReservationRequestDTO.getScheduleId());
        schedulesDTO.add(scheduleDTO);
        scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(scheduleId);
        schedulesDTO.add(scheduleDTO);
    }

    @Test
    public void bookReservation() {

        when(scheduleService.findAllSchedules()).thenReturn(schedulesDTO);
        when(reservationRepository.saveAndFlush(any(Reservation.class))).thenReturn(reservation);
        when(reservationMapper.map(any(Reservation.class))).thenReturn(reservationDTO);
        when(reservationMapper.map(any(CreateReservationRequestDTO.class))).thenReturn(reservation);

        final ReservationDTO result = reservationService.bookReservation(createReservationRequestDTO);

        verify(scheduleService).findAllSchedules();
        verify(reservationRepository).saveAndFlush(reservation);
        verify(reservationMapper).map(reservation);
        verify(reservationMapper).map(createReservationRequestDTO);

        assertEquals(reservationDTO, result);
    }

    @Test
    public void getRefundValue() {
        Schedule schedule = new Schedule();

        LocalDateTime startDateTime = LocalDateTime.now().plusHours(15);
        System.out.println(startDateTime);
        schedule.setStartDateTime(startDateTime);
        System.out.println(reservationService.getRefundValue(Reservation.builder().schedule(schedule).value(new BigDecimal(10L)).build()));
        System.out.println(BigDecimal.valueOf((double)7.50));
        Assert.assertEquals(reservationService.getRefundValue(Reservation.builder().schedule(schedule).value(new BigDecimal(10L)).build()), BigDecimal.valueOf((double)7.50));
      
    } 
    @Test
    public void cancelres()
    {
    	Random rd = new Random();
        Schedule sch = new Schedule();
        Reservation res =  new Reservation();
        reservation.setReservationStatus(ReservationStatus.CANCELLED);

        LocalDateTime startDateTime = LocalDateTime.now().minusHours(5);
        sch.setStartDateTime(startDateTime);
        reservation.getSchedule().setStartDateTime(startDateTime);
        ReservationDTO s= new ReservationDTO();
        CreateReservationRequestDTO a = new CreateReservationRequestDTO(rd.nextLong(),rd.nextLong());
        s = reservationService.bookReservation(a);
        //Assert.assertEquals(reservationService.cancelReservation(),s);
    }
    
    
}