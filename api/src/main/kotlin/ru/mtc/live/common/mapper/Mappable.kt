package ru.mtc.live.common.mapper

interface Mappable<E,D> {

    fun toDto(entity: E): D

    fun toDto(entity: List<E>?): List<D>

    fun toEntity(dto: D): E

    fun toEntity(dtos: List<D>?): List<E>
}