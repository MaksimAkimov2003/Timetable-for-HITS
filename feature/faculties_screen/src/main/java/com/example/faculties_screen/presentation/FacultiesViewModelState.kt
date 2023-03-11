package com.example.faculties_screen.presentation

sealed class FacultiesViewModelState {
	object Loading : FacultiesViewModelState()
	data class Content(
		val faculties: MutableMap<String, String>
	) : FacultiesViewModelState()
}