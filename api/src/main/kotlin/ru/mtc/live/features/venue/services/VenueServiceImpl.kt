package ru.mtc.live.features.venue.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mtc.live.common.exceptions.ResourceNotFoundException
import ru.mtc.live.common.geocalc.EarthCalc.distanceInKilometers
import ru.mtc.live.features.event.entities.feature.VenueFeatureTypeEntity
import ru.mtc.live.features.venue.entities.VenueEntity
import ru.mtc.live.features.venue.repositories.VenueRepository

@Service
class VenueServiceImpl(
    private val venueRepository: VenueRepository
): VenueService {

    @Transactional(readOnly = true)
    override fun getAll(features: List<VenueFeatureTypeEntity.Type>?): List<VenueEntity> {
        return if(!features.isNullOrEmpty())
            venueRepository.findAll().filter { venue -> venue.features.any { features.contains(it.name) } }
        else
            venueRepository.findAll()
    }

    @Transactional(readOnly = true)
    override fun getAll(
        latitude: Double,
        longitude: Double,
        radius: Double,
        features: List<VenueFeatureTypeEntity.Type>?
    ): List<VenueEntity> {
        val venues = getAll(features)

        return venues.filter { venue ->
            val distance: Double = distanceInKilometers(
                venue.latitude, venue.longitude,
                latitude, longitude
            )

            distance <= radius
        }
    }

    @Transactional(readOnly = true)
    override fun getById(id: Int): VenueEntity {
        return venueRepository.findById(id)
            .orElseGet { throw ResourceNotFoundException("Venue not found") }
    }
}