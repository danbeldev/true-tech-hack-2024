package ru.mtc.live.features.event.dto.person

import ru.mtc.live.features.person.dto.PersonDto
import ru.mtc.live.features.person.dto.post.PersonPostDto

data class EventPersonDto(
    val person: PersonDto,
    val post: PersonPostDto
)
