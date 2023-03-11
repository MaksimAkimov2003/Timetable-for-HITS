package com.example.feature.teachers_screen.data.dataSource

import com.example.feature.teachers_screen.data.api.TeachersApi
import com.example.feature.teachers_screen.data.model.TeacherModel

class TeachersDataSource(private val api: TeachersApi) {

	suspend fun getTeachersList(): List<TeacherModel> {
		return api.getTeachers()
	}
}