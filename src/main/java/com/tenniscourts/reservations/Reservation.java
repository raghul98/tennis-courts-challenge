package com.tenniscourts.reservations;

import com.tenniscourts.config.persistence.BaseEntity;
import com.tenniscourts.guests.Guest;
import com.tenniscourts.schedules.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class Reservation extends BaseEntity<Long> {
	//change it to manytoone
    @OneToOne
    private Guest guest;

    @ManyToOne
    @NotNull
    private Schedule schedule;
    

    @NotNull
    private BigDecimal value;

    @NotNull
    private ReservationStatus reservationStatus = ReservationStatus.READY_TO_PLAY;

    private BigDecimal refundValue;
    

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public BigDecimal getRefundValue() {
		return refundValue;
	}

	public void setRefundValue(BigDecimal refundValue) {
		this.refundValue = refundValue;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
    
}
