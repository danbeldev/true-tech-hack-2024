package ru.mtc.live.features.event.entities.feature

import jakarta.persistence.*
import ru.mtc.live.features.venue.entities.VenueEntity

@Entity(name = "venue_feature_types")
data class VenueFeatureTypeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Enumerated(EnumType.STRING)
    val name: Type,

    @ManyToMany(fetch = FetchType.LAZY)
    val venues: List<VenueEntity>
) {
    enum class Type {
        WHEELCHAIR,
        BLIND
    }
}
