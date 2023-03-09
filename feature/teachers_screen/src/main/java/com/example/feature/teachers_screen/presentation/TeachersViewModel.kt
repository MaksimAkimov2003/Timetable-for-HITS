package com.example.feature.teachers_screen.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.teachers_screen.domain.useCase.GetTeachersListUseCase
import kotlinx.coroutines.launch

class TeachersViewModel(
	private val getTeachersListUseCase: GetTeachersListUseCase
) : ViewModel() {

	private val _state = MutableLiveData<TeachersViewModelState>(TeachersViewModelState.Loading)
	val state: LiveData<TeachersViewModelState> = _state

	fun getTeachers() {
		Log.e("getTeachers", "YES")

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

}