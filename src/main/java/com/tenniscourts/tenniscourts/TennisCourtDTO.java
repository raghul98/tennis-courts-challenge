package com.tenniscourts.tenniscourts;

import com.tenniscourts.schedules.ScheduleDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TennisCourtDTO {

    private Long id;

    @NotNull
    private String name;
    

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private List<ScheduleDTO> tennisCourtSchedules;

	public List<ScheduleDTO> getTennisCourtSchedules() {
		return tennisCourtSchedules;
	}

	public void setTennisCourtSchedules(List<ScheduleDTO> tennisCourtSchedules) {
		this.tennisCourtSchedules = tennisCourtSchedules;
	}
    
    
}
