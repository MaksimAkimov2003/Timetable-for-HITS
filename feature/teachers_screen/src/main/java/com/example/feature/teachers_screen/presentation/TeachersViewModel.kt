package com.example.feature.teachers_screen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.teachers_screen.domain.useCase.GetTeachersListUseCase
import com.example.userstorage.domain.entity.TimetableType
import com.example.userstorage.domain.entity.UserData
import com.example.userstorage.domain.usecase.SaveUserDataUseCase
import kotlinx.coroutines.launch

class TeachersViewModel(
	private val getTeachersListUseCase: GetTeachersListUseCase,
	private val saveUserDataUseCase: SaveUserDataUseCase
) : ViewModel() {

	private val _state = MutableLiveData<TeachersViewModelState>(TeachersViewModelState.Loading)
	val state: LiveData<TeachersViewModelState> = _state

	fun getTeachers() {
		viewModelScope.launch {
			val result = getTeachersListUseCase.execute()
			val teachersMap = mutableMapOf<String, String>()

			for (teacherEntity in result) {
				teachersMap.put(
					key = teacherEntity.id,
					value = teacherEntity.name
				)
			}

			_state.value = TeachersViewModelState.Content(
				teachers = teachersMap
			)
		}

	}

	fun saveData(teacherName: String) {
		val data = UserData(data = TimetableType.Teacher)
		data.data.value = teacherName

		saveUserDataUseCase(data)
	}

}