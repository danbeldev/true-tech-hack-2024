package ru.mtc.live.features.event.mappers

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import ru.mtc.live.common.mapper.Mappable
import ru.mtc.live.features.event.dto.EventDto
import ru.mtc.live.features.event.entities.EventEntity
import ru.mtc.live.features.event.mappers.category.CategoryMapper

@Mapper(componentModel = "spring", uses = [CategoryMapper::class])
interface EventMapper: Mappable<EventEntity, EventDto> {

    @Mapping(source = "entity.status.name", target = "status")
    @Mapping(expression = "java(entity.getFirstImagePublicUrl())", target = "image")
    override fun toDto(entity: EventEntity): EventDto
}