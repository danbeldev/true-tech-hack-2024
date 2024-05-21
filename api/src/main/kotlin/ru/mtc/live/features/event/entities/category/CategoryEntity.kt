package ru.mtc.live.features.event.entities.category

import jakarta.persistence.*
import ru.mtc.live.features.event.entities.EventEntity

@Entity(name = "categories")
data class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String,

    @ManyToMany(fetch = FetchType.LAZY)
    val events: List<EventEntity>
)
