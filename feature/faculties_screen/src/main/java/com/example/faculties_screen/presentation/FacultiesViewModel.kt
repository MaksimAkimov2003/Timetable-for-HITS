package com.example.faculties_screen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.faculties_screen.domain.useCase.GetFacultiesListUseCase
import kotlinx.coroutines.launch

class FacultiesViewModel(
	private val getFacultiesListUseCase: GetFacultiesListUseCase
) : ViewModel() {

	private val _state = MutableLiveData<FacultiesViewModelState>(FacultiesViewModelState.Loading)
	val state: LiveData<FacultiesViewModelState> = _state

	fun getFaculties() {

		viewModelScope.launch {
			val result = getFacultiesListUseCase.execute()
			val facultiesMap = mutableMapOf<String, String>()

			for (facultyEntity in result) {
				facultiesMap.put(
					key = facultyEntity.id,
					value = facultyEntity.name
				)
			}

			_state.value = FacultiesViewModelState.Content(
				faculties = facultiesMap
			)
		}

	}

}