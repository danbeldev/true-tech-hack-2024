package ru.mtc.live.features.event.dto

import ru.mtc.live.features.event.dto.category.CategoryDto
import ru.mtc.live.features.event.dto.person.EventPersonDto
import ru.mtc.live.features.event.dto.schedule.EventScheduleDto
import ru.mtc.live.features.event.entities.status.EventStatusEntity

data class EventDetailsDto(
    val id: Int,
    val name: String,
    val description: String,
    val status: EventStatusEntity.Status,
    val images: List<String>,
    val categories: List<CategoryDto>,
    val schedules: List<EventScheduleDto>,
    val persons: List<EventPersonDto>
)
