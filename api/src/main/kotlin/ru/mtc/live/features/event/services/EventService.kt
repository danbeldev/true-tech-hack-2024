package ru.mtc.live.features.event.services

import ru.mtc.live.features.event.entities.EventEntity
import ru.mtc.live.features.event.entities.status.EventStatusEntity

interface EventService {

    fun getById(id: Int): EventEntity

    fun getAll(
        venueId: Int,
        status: EventStatusEntity.Status
    ): List<EventEntity>
}