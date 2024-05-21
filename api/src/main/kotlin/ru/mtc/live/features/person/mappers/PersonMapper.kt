package ru.mtc.live.features.person.mappers

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import ru.mtc.live.common.mapper.Mappable
import ru.mtc.live.features.person.dto.PersonDto
import ru.mtc.live.features.person.entities.PersonEntity

@Mapper(componentModel = "spring")
interface PersonMapper: Mappable<PersonEntity, PersonDto> {

    @Mapping(expression = "java(entity.getPhotoPublicUrl())", target = "photo")
    override fun toDto(entity: PersonEntity): PersonDto

    @Mapping(target = "photo", ignore = true)
    override fun toEntity(dto: PersonDto): PersonEntity
}