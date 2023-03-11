package com.example.auditories_screen.data.mapper

import com.example.auditories_screen.data.model.AuditoryModel
import com.example.auditories_screen.domain.entity.AuditoryEntity

fun List<AuditoryModel>.toEntity(): List<AuditoryEntity> = map {
	AuditoryEntity(
		number = it.number
	)
}