package com.example.faculties_screen.data.repository

import com.example.faculties_screen.data.dataSource.FacultiesDataSource
import com.example.faculties_screen.data.mapper.toEntity
import com.example.faculties_screen.domain.entity.FacultyEntity

class FacultiesRepository(private val dataSource: FacultiesDataSource) {

	suspend fun getFacultiesFromApi(): List<FacultyEntity> {
		return dataSource.getFacultiesList().toEntity()
	}
}