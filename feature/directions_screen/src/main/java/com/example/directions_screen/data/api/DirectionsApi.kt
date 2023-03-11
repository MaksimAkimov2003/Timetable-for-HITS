package com.example.directions_screen.data.api

import com.example.directions_screen.data.model.DirectionModel
import retrofit2.http.GET
import retrofit2.http.Path

interface DirectionsApi {

	@GET("/api/disciplines/{facultyId}")
	suspend fun getDirections(
		@Path("facultyId")
		facultyId: String
	): List<DirectionModel>
}