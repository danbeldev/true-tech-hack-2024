package ru.mtc.live.features.storage.entities

import jakarta.persistence.*
import ru.mtc.live.common.Utils.getCurrentServerAddress
import ru.mtc.live.features.event.entities.EventEntity
import ru.mtc.live.features.person.entities.PersonEntity

@Entity(name = "files")
data class FileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String,

    @ManyToMany(fetch = FetchType.LAZY)
    val events: List<EventEntity>,

    @OneToMany(fetch = FetchType.LAZY)
    val persons: List<PersonEntity>,
) {
    fun getPublicUrl(): String = "${getCurrentServerAddress()}/files/$name"
}
