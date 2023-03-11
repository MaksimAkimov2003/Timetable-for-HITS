package com.example.groups_screen.data.api

import com.example.groups_screen.data.model.GroupModel
import retrofit2.http.GET
import retrofit2.http.Path

interface GroupsApi {

	@GET("/api/groups/{disciplineId}")
	suspend fun getDirections(
		@Path("disciplineId")
		disciplineId: String
	): List<GroupModel>
}