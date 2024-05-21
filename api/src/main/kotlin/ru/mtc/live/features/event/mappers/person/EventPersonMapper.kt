package ru.mtc.live.features.event.mappers.person

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import ru.mtc.live.common.mapper.Mappable
import ru.mtc.live.features.event.dto.person.EventPersonDto
import ru.mtc.live.features.event.entities.person.EventPersonEntity
import ru.mtc.live.features.person.mappers.PersonMapper
import ru.mtc.live.features.person.mappers.post.PersonPostMapper

@Mapper(componentModel = "spring", uses = [PersonMapper::class, PersonPostMapper::class])
interface EventPersonMapper: Mappable<EventPersonEntity, EventPersonDto> {

    @Mapping(source = "entity.id.person", target = "person")
    @Mapping(source = "entity.id.post", target = "post")
    override fun toDto(entity: EventPersonEntity): EventPersonDto
}