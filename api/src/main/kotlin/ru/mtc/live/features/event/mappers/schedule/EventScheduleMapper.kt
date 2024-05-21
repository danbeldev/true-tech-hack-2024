package ru.mtc.live.features.event.mappers.schedule

import org.mapstruct.Mapper
import ru.mtc.live.common.mapper.Mappable
import ru.mtc.live.features.event.dto.schedule.EventScheduleDto
import ru.mtc.live.features.event.entities.schedule.EventScheduleEntity

@Mapper(componentModel = "spring")
interface EventScheduleMapper: Mappable<EventScheduleEntity, EventScheduleDto>