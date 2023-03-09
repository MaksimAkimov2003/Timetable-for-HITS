package com.example.directions_screen.domain.useCase

import com.example.directions_screen.data.repository.DirectionsRepository
import com.example.directions_screen.domain.entity.DirectionEntity

class GetDirectionsListUseCase(private val repository: DirectionsRepository) {

	suspend fun execute(facultyId: String): List<DirectionEntity> {
		return repository.getDirectionsFromApi(facultyId)
	}
}