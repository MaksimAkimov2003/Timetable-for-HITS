package com.example.directions_screen.data.repository

import com.example.directions_screen.data.dataSource.DirectionDataSource
import com.example.directions_screen.data.mapper.toEntity
import com.example.directions_screen.domain.entity.DirectionEntity

class DirectionsRepository(private val dataSource: DirectionDataSource) {

	suspend fun getDirectionsFromApi(facultyId: String): List<DirectionEntity> {
		return dataSource.getFacultiesList(facultyId).toEntity()
	}
}