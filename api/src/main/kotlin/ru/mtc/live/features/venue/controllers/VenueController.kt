package ru.mtc.live.features.venue.controllers

import io.swagger.v3.oas.annotations.Parameter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.mtc.live.features.event.entities.feature.VenueFeatureTypeEntity
import ru.mtc.live.features.venue.dto.VenueDto
import ru.mtc.live.features.venue.mappers.VenueMapper
import ru.mtc.live.features.venue.services.VenueService

@[RestController RequestMapping("venues")]
class VenueController(
    val venueService: VenueService,

    val venueMapper: VenueMapper
) {

    @GetMapping
    fun getAll(
        @RequestParam(required = false)
        features: List<VenueFeatureTypeEntity.Type>?
    ): List<VenueDto> = venueMapper.toDto(venueService.getAll(features))

    @GetMapping("search")
    fun getAll(
        @[RequestParam Parameter(description = "Географическая широта центра круга поиска")]
        lat: Double,
        @[RequestParam Parameter(description = "Географическая долгота центра круга поиска")]
        long: Double,
        @[RequestParam Parameter(description = "Радиус круга поиска")]
        radios: Double,
        @RequestParam(required = false)
        features: List<VenueFeatureTypeEntity.Type>?
    ) = venueMapper.toDto(venueService.getAll(lat, long, radios, features))
}