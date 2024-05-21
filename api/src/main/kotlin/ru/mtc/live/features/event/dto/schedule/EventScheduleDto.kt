package ru.mtc.live.features.event.dto.schedule

import java.time.LocalDateTime

data class EventScheduleDto(
    val id: Int,
    val dateTime: LocalDateTime
)
