package ru.mtc.live.features.venue.dto

import ru.mtc.live.features.event.entities.feature.VenueFeatureTypeEntity

data class VenueDto(
    val id: Int,
    val name: String,
    val longitude: Double,
    val latitude: Double,
    val features: List<VenueFeatureTypeEntity.Type>
)
