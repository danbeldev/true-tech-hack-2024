package ru.mtc.live.features.event.dto

import ru.mtc.live.features.event.dto.category.CategoryDto
import ru.mtc.live.features.event.entities.status.EventStatusEntity

data class EventDto(
    val id: Int,
    val name: String,
    val status: EventStatusEntity.Status,
    val image: String?,
    val categories: List<CategoryDto>
)
