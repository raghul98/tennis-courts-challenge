package com.tenniscourts.schedules;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    Schedule map(ScheduleDTO source);

    ScheduleDTO map(Schedule source);

    List<ScheduleDTO> map(List<Schedule> source);
    @Mapping(target = "tennisCourt.id", source = "tennisCourtId")
    @Mapping(target = "endDateTime", expression = "java(source.getStartDateTime().plusHours(1))")
    Schedule map(CreateScheduleRequestDTO source);
}
