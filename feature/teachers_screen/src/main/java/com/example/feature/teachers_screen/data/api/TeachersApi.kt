package com.example.feature.teachers_screen.data.api

import com.example.feature.teachers_screen.data.model.TeacherModel
import retrofit2.http.GET

interface TeachersApi {

	@GET("/api/teachers")
	suspend fun getTeachers(): List<TeacherModel>
}