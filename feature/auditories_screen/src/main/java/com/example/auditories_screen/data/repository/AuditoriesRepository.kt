package com.example.auditories_screen.data.repository

import com.example.auditories_screen.data.dataSource.AuditoriesDataSource
import com.example.auditories_screen.data.mapper.toEntity
import com.example.auditories_screen.domain.entity.AuditoryEntity

class AuditoriesRepository(private val dataSource: AuditoriesDataSource) {

	suspend fun getAuditoriesFromApi(): List<AuditoryEntity> {
		return dataSource.getAuditoriesList().toEntity()
	}
}