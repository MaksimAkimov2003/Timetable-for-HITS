package com.example.faculties_screen.domain.useCase

import com.example.faculties_screen.data.repository.FacultiesRepository
import com.example.faculties_screen.domain.entity.FacultyEntity

class GetFacultiesListUseCase(private val repository: FacultiesRepository) {

	suspend fun execute(): List<FacultyEntity> {
		return repository.getFacultiesFromApi()
	}
}