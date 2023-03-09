package com.example.feature.teachers_screen.data.repository

import com.example.feature.teachers_screen.data.dataSource.TeachersDataSource
import com.example.feature.teachers_screen.data.mapper.toEntity
import com.example.feature.teachers_screen.domain.entity.TeacherEntity

class TeachersRepository(private val dataSource: TeachersDataSource) {

	suspend fun getTeachersFromApi(): List<TeacherEntity> {
		return dataSource.getTeachersList().toEntity()
	}
}