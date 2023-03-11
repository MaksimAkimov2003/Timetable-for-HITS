package com.example.faculties_screen.data.api

import com.example.faculties_screen.data.model.FacultyModel
import retrofit2.http.GET

interface FacultiesApi {

	@GET("/api/faculties")
	suspend fun getFaculties(): List<FacultyModel>
}