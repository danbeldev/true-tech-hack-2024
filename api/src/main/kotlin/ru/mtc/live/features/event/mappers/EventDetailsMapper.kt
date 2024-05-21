package ru.mtc.live.features.event.mappers

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import ru.mtc.live.common.mapper.Mappable
import ru.mtc.live.features.event.dto.EventDetailsDto
import ru.mtc.live.features.event.entities.EventEntity
import ru.mtc.live.features.event.mappers.category.CategoryMapper
import ru.mtc.live.features.event.mappers.person.EventPersonMapper
import ru.mtc.live.features.event.mappers.schedule.EventScheduleMapper

@Mapper(componentModel = "spring", uses = [
    CategoryMapper::class,
    EventScheduleMapper::class,
    EventPersonMapper::class
])
interface EventDetailsMapper: Mappable<EventEntity, EventDetailsDto> {

    @Mapping(source = "entity.status.name", target = "status")
    @Mapping(expression = "java(entity.getImages().stream().map(i -> i.getPublicUrl()).toList())", target = "images")
    override fun toDto(entity: EventEntity): EventDetailsDto

    @Mapping(target = "images", ignore = true)
    override fun toEntity(dto: EventDetailsDto): EventEntity
}