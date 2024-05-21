package ru.mtc.live.features.person.dto

data class PersonDto(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val photo: String?
)
