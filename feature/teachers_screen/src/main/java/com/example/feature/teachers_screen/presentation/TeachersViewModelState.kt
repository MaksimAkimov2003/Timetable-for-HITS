package com.example.feature.teachers_screen.presentation

sealed class TeachersViewModelState {
	object Loading : TeachersViewModelState()
	data class Content(
		val teachers: MutableMap<String, String>
	) : TeachersViewModelState()
}