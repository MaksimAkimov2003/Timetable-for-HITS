package com.example.faculties_screen.data.mapper

import com.example.faculties_screen.data.model.FacultyModel
import com.example.faculties_screen.domain.entity.FacultyEntity

private fun FacultyModel.toEntity(): FacultyEntity =
	FacultyEntity(
		id = id,
		name = name
	)

fun List<FacultyModel>.toEntity(): List<FacultyEntity> = map {
	FacultyEntity(
		id = it.id,
		name = it.name
	)
}