package ru.mtc.live.features.venue.services

import ru.mtc.live.features.event.entities.feature.VenueFeatureTypeEntity
import ru.mtc.live.features.venue.entities.VenueEntity

interface VenueService {

    fun getAll(features: List<VenueFeatureTypeEntity.Type>? = null): List<VenueEntity>

    fun getAll(
        latitude: Double,
        longitude: Double,
        radius: Double,
        features: List<VenueFeatureTypeEntity.Type>? = null
    ): List<VenueEntity>

    fun getById(id: Int): VenueEntity
}