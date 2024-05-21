package ru.mtc.live.features.venue.mappers

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import ru.mtc.live.common.mapper.Mappable
import ru.mtc.live.features.event.mappers.EventDetailsMapper
import ru.mtc.live.features.venue.dto.VenueDetailsDto
import ru.mtc.live.features.venue.entities.VenueEntity

@Mapper(componentModel = "spring", uses = [EventDetailsMapper::class])
interface VenueDetailsMapper: Mappable<VenueEntity, VenueDetailsDto> {

    @Mapping(expression = "java(entity.getFeatures().stream().map(f -> f.getName()).toList())", target = "features")
    override fun toDto(entity: VenueEntity): VenueDetailsDto
}