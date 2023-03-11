package com.example.feature.teachers_screen.domain.useCase

import com.example.feature.teachers_screen.data.repository.TeachersRepository
import com.example.feature.teachers_screen.domain.entity.TeacherEntity

class GetTeachersListUseCase(private val repository: TeachersRepository) {

	suspend fun execute(): List<TeacherEntity> {
		return repository.getTeachersFromApi()
	}
}