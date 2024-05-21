package ru.mtc.live.features.event.controllers

import org.springframework.web.bind.annotation.*
import ru.mtc.live.features.event.dto.EventDto
import ru.mtc.live.features.event.entities.status.EventStatusEntity
import ru.mtc.live.features.event.mappers.EventDetailsMapper
import ru.mtc.live.features.event.mappers.EventMapper
import ru.mtc.live.features.event.services.EventService

@[RestController RequestMapping("events")]
class EventController(
    val eventService: EventService,

    val eventMapper : EventMapper,
    val eventDetailsMapper: EventDetailsMapper
) {

    @GetMapping
    fun getAll(
        @RequestParam
        venueId: Int,
        @RequestParam
        status: EventStatusEntity.Status = EventStatusEntity.Status.ACTUAL
    ): List<EventDto> = eventMapper.toDto(eventService.getAll(venueId, status))

    @GetMapping("{id}")
    fun getById(
        @PathVariable("id") id: Int
    ) = eventDetailsMapper.toDto(eventService.getById(id))
}