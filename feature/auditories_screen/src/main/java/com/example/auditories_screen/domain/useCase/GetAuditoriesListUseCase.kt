package com.example.auditories_screen.domain.useCase

import com.example.auditories_screen.data.repository.AuditoriesRepository
import com.example.auditories_screen.domain.entity.AuditoryEntity

class GetAuditoriesListUseCase(private val repository: AuditoriesRepository) {

	suspend fun execute(): List<AuditoryEntity> {
		return repository.getAuditoriesFromApi()
	}
}