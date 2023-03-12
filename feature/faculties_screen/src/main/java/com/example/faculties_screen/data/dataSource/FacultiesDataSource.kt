package com.example.faculties_screen.data.dataSource

import com.example.faculties_screen.data.api.FacultiesApi
import com.example.faculties_screen.data.model.FacultyModel

class FacultiesDataSource(private val api: FacultiesApi) {

	suspend fun getFacultiesList(): List<FacultyModel> {
		return api.getFaculties()
	}
}