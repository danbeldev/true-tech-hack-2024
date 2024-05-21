package ru.mtc.live.features.event.entities

import jakarta.persistence.*
import ru.mtc.live.features.event.entities.category.CategoryEntity
import ru.mtc.live.features.event.entities.person.EventPersonEntity
import ru.mtc.live.features.event.entities.schedule.EventScheduleEntity
import ru.mtc.live.features.event.entities.status.EventStatusEntity
import ru.mtc.live.features.storage.entities.FileEntity
import ru.mtc.live.features.venue.entities.VenueEntity

@Entity(name = "events")
data class EventEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String,
    val description: String,

    @ManyToOne(fetch = FetchType.LAZY)
    val venue: VenueEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    val status: EventStatusEntity,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "events_categories",
        joinColumns = [JoinColumn(name = "event_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")]
    )
    val categories: List<CategoryEntity>,

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    val schedules: List<EventScheduleEntity>,

    @OneToMany(mappedBy = "id.event", fetch = FetchType.LAZY)
    val persons: List<EventPersonEntity>,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "events_images",
        joinColumns = [JoinColumn(name = "event_id")],
        inverseJoinColumns = [JoinColumn(name = "image_id")]
    )
    val images: List<FileEntity>
) {
    fun getFirstImagePublicUrl(): String? = images.firstOrNull()?.getPublicUrl()
}
