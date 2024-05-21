package ru.mtc.live.features.venue.entities

import jakarta.persistence.*
import ru.mtc.live.features.event.entities.EventEntity
import ru.mtc.live.features.event.entities.feature.VenueFeatureTypeEntity

@Entity(name = "venues")
data class VenueEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String,
    val longitude: Double,
    val latitude: Double,

    @OneToMany(mappedBy = "venue", fetch = FetchType.LAZY)
    val events: List<EventEntity>,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "venues_features",
        joinColumns = [JoinColumn(name = "venue_id")],
        inverseJoinColumns = [JoinColumn(name = "type_id")]
    )
    val features: List<VenueFeatureTypeEntity>
)