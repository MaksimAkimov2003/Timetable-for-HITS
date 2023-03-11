package com.example.groups_screen.presentation

sealed class GroupsViewModelState {
	object Loading : GroupsViewModelState()
	data class Content(
		val groups: List<String>
	) : GroupsViewModelState()
}