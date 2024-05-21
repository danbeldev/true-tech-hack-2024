package ru.mtc.live.features.venue.dto

import ru.mtc.live.features.event.dto.EventDetailsDto
import ru.mtc.live.features.event.entities.feature.VenueFeatureTypeEntity

data class VenueDetailsDto(
    val id: Int,
    val name: String,
    val longitude: Double,
    val latitude: Double,
    val features: List<VenueFeatureTypeEntity.Type>,
    val events: List<EventDetailsDto>
)
