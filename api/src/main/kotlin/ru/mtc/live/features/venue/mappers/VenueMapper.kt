package ru.mtc.live.features.venue.mappers

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import ru.mtc.live.common.mapper.Mappable
import ru.mtc.live.features.venue.dto.VenueDto
import ru.mtc.live.features.venue.entities.VenueEntity

@Mapper(componentModel = "spring")
interface VenueMapper: Mappable<VenueEntity, VenueDto> {

    @Mapping(expression = "java(entity.getFeatures().stream().map(f -> f.getName()).toList())", target = "features")
    override fun toDto(entity: VenueEntity): VenueDto
}