package com.example.groups_screen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groups_screen.domain.useCase.GetGroupsListUseCase
import com.example.userstorage.domain.entity.TimetableType
import com.example.userstorage.domain.entity.UserData
import com.example.userstorage.domain.usecase.SaveUserDataUseCase
import kotlinx.coroutines.launch

class GroupsViewModel(
	private val getGroupsListUseCase: GetGroupsListUseCase,
	private val saveUserDataUseCase: SaveUserDataUseCase
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

	fun saveData(groupNumber: String) {
		val data = UserData(data = TimetableType.Group)
		data.data.value = groupNumber

		saveUserDataUseCase.invoke(data = data)
	}

}