package ru.mtc.live.features.event.entities.schedule

import jakarta.persistence.*
import ru.mtc.live.features.event.entities.EventEntity
import java.time.LocalDateTime

@Entity(name = "event_schedules")
data class EventScheduleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val dateTime: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    val event: EventEntity
)
