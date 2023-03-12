package com.example.auditories_screen.data.api

import com.example.auditories_screen.data.model.AuditoryModel
import retrofit2.http.GET

interface AuditoriesApi {

	@GET("/api/auditories")
	suspend fun getAuditories(): List<AuditoryModel>
}