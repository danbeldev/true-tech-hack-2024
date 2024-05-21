package ru.mtc.live.features.event.services

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mtc.live.common.exceptions.ResourceNotFoundException
import ru.mtc.live.features.event.entities.EventEntity
import ru.mtc.live.features.event.entities.status.EventStatusEntity
import ru.mtc.live.features.event.repositories.EventRepository

@Service
class EventServiceImpl(
    val eventRepository: EventRepository
): EventService {

    @Transactional(readOnly = true)
    override fun getById(id: Int): EventEntity {
        return eventRepository.findByIdOrNull(id) ?: throw ResourceNotFoundException("Event by $id not found")
    }

    @Transactional(readOnly = true)
    override fun getAll(
        venueId: Int,
        status: EventStatusEntity.Status
    ): List<EventEntity> {
        return eventRepository.findAll()
            .filter { event -> event.venue.id == venueId && event.status.name == status }
    }
}