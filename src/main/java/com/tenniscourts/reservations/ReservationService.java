package com.tenniscourts.reservations;

import com.tenniscourts.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Service
@AllArgsConstructor
public class ReservationService {
	@Autowired
    private final ReservationRepository reservationRepository;
	
	@Autowired
    private final ReservationMapper reservationMapper;

    public ReservationDTO bookReservation(CreateReservationRequestDTO createReservationRequestDTO) {
        
    	return reservationMapper.map(reservationRepository.saveAndFlush(reservationMapper.map(createReservationRequestDTO)));
    }

    public ReservationDTO findReservation(Long reservationId) {
        return reservationRepository.findById(reservationId).map(reservationMapper::map).orElseThrow(() -> {
            throw new EntityNotFoundException("Reservation not found.");
        });
    }

    public ReservationDTO cancelReservation(Long reservationId) {
        return reservationMapper.map(this.cancel(reservationId));
    }

    private Reservation cancel(Long reservationId) {
        return reservationRepository.findById(reservationId).map(reservation -> {

            this.validateCancellation(reservation);

            BigDecimal refundValue = getRefundValue(reservation);
            return this.updateReservation(reservation, refundValue, ReservationStatus.CANCELLED);

        }).orElseThrow(() -> {
            throw new EntityNotFoundException("Reservation not found.");
        });
    }

    private Reservation updateReservation(Reservation reservation, BigDecimal refundValue, ReservationStatus status) {
        reservation.setReservationStatus(status);
        reservation.setValue(reservation.getValue().subtract(refundValue));
        reservation.setRefundValue(refundValue);

        return reservationRepository.save(reservation);
    }

    private void validateCancellation(Reservation reservation) {
        if (!ReservationStatus.READY_TO_PLAY.equals(reservation.getReservationStatus())) {
            throw new IllegalArgumentException("Cannot cancel/reschedule because it's not in ready to play status.");
        }

        if (reservation.getSchedule().getStartDateTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Can cancel/reschedule only future dates.");
        }
    }

    public BigDecimal getRefundValue(Reservation reservation) {
        long hours = ChronoUnit.HOURS.between(LocalDateTime.now(), reservation.getSchedule().getStartDateTime());

        if (hours >= 24) {
            return reservation.getValue();
        }
        if (hours>=12 && hours<24) {
        	BigDecimal val = reservation.getValue();
        	return val.subtract(val.multiply(BigDecimal.valueOf((double)25/100)));
        }
        if (hours>=2 && hours<12) {
        	BigDecimal val = reservation.getValue();
        	return val.subtract(val.multiply(BigDecimal.valueOf((double)50/100)));
        }
        if (hours>0 && hours<2) {
        	BigDecimal val = reservation.getValue();
        	return val.subtract(val.multiply(BigDecimal.valueOf((double)75/100)));
        }

        return BigDecimal.ZERO;
    }

    public ReservationDTO rescheduleReservation(Long previousReservationId, Long scheduleId) {
    	//Gets the previous reservation by calling findbyid() 
        Reservation previous = reservationRepository.findById(previousReservationId).orElseThrow(() ->
        new EntityNotFoundException("Reservation not found.")
    );
        //compares schedule id to the previous schedule id
        if (scheduleId.equals(previous.getSchedule().getId())) {
            throw new IllegalArgumentException("Cannot reschedule to the same slot.");
        }
        //validates update with previous reservation
        this.validateCancellation(previous);
        //updates reservation with new data
        previous = this.updateReservation(previous, getRefundValue(previous), ReservationStatus.RESCHEDULED);
        //creates new reservation and books it
        ReservationDTO newres = bookReservation(CreateReservationRequestDTO.builder()
                .guestId(previous.getGuest().getId())
                .scheduleId(scheduleId)
                .build());
        //now set previous reservation with previous
        newres.setPreviousReservation(reservationMapper.map(previous));
        return newres;
    }
}
