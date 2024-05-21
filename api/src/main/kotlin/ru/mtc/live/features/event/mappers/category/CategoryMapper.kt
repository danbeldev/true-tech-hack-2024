package ru.mtc.live.features.event.mappers.category

import org.mapstruct.Mapper
import ru.mtc.live.common.mapper.Mappable
import ru.mtc.live.features.event.dto.category.CategoryDto
import ru.mtc.live.features.event.entities.category.CategoryEntity

@Mapper(componentModel = "spring")
interface CategoryMapper: Mappable<CategoryEntity, CategoryDto>