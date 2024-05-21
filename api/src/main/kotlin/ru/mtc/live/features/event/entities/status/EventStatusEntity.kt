package ru.mtc.live.features.event.entities.status

import jakarta.persistence.*
import ru.mtc.live.features.event.entities.EventEntity

@Entity(name = "event_status")
data class EventStatusEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Short,
    @Enumerated(EnumType.STRING)
    val name: Status,

    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    val events: List<EventEntity>
) {
    enum class Status {
        ACTUAL
    }
}
