package com.example.auditories_screen.data.dataSource

import com.example.auditories_screen.data.api.AuditoriesApi
import com.example.auditories_screen.data.model.AuditoryModel

class AuditoriesDataSource(private val api: AuditoriesApi) {

	suspend fun getAuditoriesList(): List<AuditoryModel> {
		return api.getAuditories()
	}
}