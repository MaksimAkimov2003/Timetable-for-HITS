package com.example.directions_screen.data.dataSource

import com.example.directions_screen.data.api.DirectionsApi
import com.example.directions_screen.data.model.DirectionModel

class DirectionDataSource(private val api: DirectionsApi) {

	suspend fun getFacultiesList(facultyId: String): List<DirectionModel> {
		return api.getDirections(facultyId)
	}
}