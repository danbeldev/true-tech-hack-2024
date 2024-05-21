package ru.mtc.live.features.person.entities

import jakarta.persistence.*
import ru.mtc.live.features.storage.entities.FileEntity

@Entity(name = "persons")
data class PersonEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val description: String?,

    @ManyToOne(fetch = FetchType.LAZY)
    val photo: FileEntity?
) {
    fun getPhotoPublicUrl(): String? = photo?.getPublicUrl()
}
