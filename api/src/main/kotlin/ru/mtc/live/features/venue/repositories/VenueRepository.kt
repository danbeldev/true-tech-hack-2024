package ru.mtc.live.features.venue.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.mtc.live.features.venue.entities.VenueEntity

interface VenueRepository: JpaRepository<VenueEntity, Int>
