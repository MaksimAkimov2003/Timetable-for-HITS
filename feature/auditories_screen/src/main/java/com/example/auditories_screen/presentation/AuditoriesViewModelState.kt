package com.example.auditories_screen.presentation

sealed class AuditoriesViewModelState {
	object Loading : AuditoriesViewModelState()
	data class Content(
		val auditories: List<String>
	) : AuditoriesViewModelState()
}