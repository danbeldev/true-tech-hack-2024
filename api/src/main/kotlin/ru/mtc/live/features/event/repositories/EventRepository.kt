package ru.mtc.live.features.event.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.mtc.live.features.event.entities.EventEntity

interface EventRepository: JpaRepository<EventEntity, Int>