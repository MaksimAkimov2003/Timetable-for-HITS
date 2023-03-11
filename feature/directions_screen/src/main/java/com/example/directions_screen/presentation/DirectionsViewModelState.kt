package com.example.directions_screen.presentation

sealed class DirectionsViewModelState {
	object Loading : DirectionsViewModelState()
	data class Content(
		val directions: MutableMap<String, String>
	) : DirectionsViewModelState()
}