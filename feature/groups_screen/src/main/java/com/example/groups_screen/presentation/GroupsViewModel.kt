package com.example.groups_screen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groups_screen.domain.useCase.GetGroupsListUseCase
import kotlinx.coroutines.launch

class GroupsViewModel(
	private val getGroupsListUseCase: GetGroupsListUseCase
) : ViewModel() {

	private val _state = MutableLiveData<GroupsViewModelState>(GroupsViewModelState.Loading)
	val state: LiveData<GroupsViewModelState> = _state

	fun getGroups(directionId: String) {

		viewModelScope.launch {

			_state.value = GroupsViewModelState.Content(
				groups = getGroupsListUseCase.execute(directionId).map { it.number.toString() }
			)
		}

	}

}