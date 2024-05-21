package ru.mtc.live.features.person.mappers.post

import org.mapstruct.Mapper
import ru.mtc.live.common.mapper.Mappable
import ru.mtc.live.features.person.dto.post.PersonPostDto
import ru.mtc.live.features.person.entities.post.PersonPostEntity

@Mapper(componentModel = "spring")
interface PersonPostMapper: Mappable<PersonPostEntity, PersonPostDto>