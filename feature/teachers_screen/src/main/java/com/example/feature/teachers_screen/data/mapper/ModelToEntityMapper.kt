package com.example.feature.teachers_screen.data.mapper

import com.example.feature.teachers_screen.data.model.TeacherModel
import com.example.feature.teachers_screen.domain.entity.TeacherEntity

private fun TeacherModel.toEntity(): TeacherEntity =
	TeacherEntity(
		id = id,
		name = name
	)

fun List<TeacherModel>.toEntity(): List<TeacherEntity> = map {
	TeacherEntity(
		id = it.id,
		name = it.name
	)
}