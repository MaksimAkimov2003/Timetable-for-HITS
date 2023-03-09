package com.example.directions_screen.data.mapper

import com.example.directions_screen.data.model.DirectionModel
import com.example.directions_screen.domain.entity.DirectionEntity

private fun DirectionModel.toEntity(): DirectionEntity =
	DirectionEntity(
		id = id,
		name = name
	)

fun List<DirectionModel>.toEntity(): List<DirectionEntity> = map {
	DirectionEntity(
		id = it.id,
		name = it.name
	)
}