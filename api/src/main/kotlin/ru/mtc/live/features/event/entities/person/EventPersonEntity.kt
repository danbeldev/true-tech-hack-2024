package ru.mtc.live.features.event.entities.person

import jakarta.persistence.*
import ru.mtc.live.features.event.entities.EventEntity
import ru.mtc.live.features.person.entities.PersonEntity
import ru.mtc.live.features.person.entities.post.PersonPostEntity
import java.io.Serializable

@Entity(name = "events_persons")
data class EventPersonEntity(
    @EmbeddedId
    val id: Id
): Serializable {

    @Embeddable
    data class Id(
        @ManyToOne(fetch = FetchType.LAZY)
        val event: EventEntity,

        @ManyToOne(fetch = FetchType.LAZY)
        val person: PersonEntity,

        @ManyToOne(fetch = FetchType.LAZY)
        val post: PersonPostEntity
    ): Serializable
}
