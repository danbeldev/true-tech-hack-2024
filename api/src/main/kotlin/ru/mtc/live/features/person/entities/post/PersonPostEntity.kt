package ru.mtc.live.features.person.entities.post

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "posts")
data class PersonPostEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Short,
    val name: String
)
