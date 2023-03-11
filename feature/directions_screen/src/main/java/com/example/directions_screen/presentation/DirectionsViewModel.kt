package com.example.directions_screen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.directions_screen.domain.useCase.GetDirectionsListUseCase
import kotlinx.coroutines.launch

class DirectionsViewModel(
	private val getDirectionsListUseCase: GetDirectionsListUseCase
) : ViewModel() {

	private val _state = MutableLiveData<DirectionsViewModelState>(DirectionsViewModelState.Loading)
	val state: LiveData<DirectionsViewModelState> = _state

	fun getDirections(facultyId: String) {

		viewModelScope.launch {
			val result = getDirectionsListUseCase.execute(facultyId)
			val directionsMap = mutableMapOf<String, String>()

			for (directionEntity in result) {
				directionsMap.put(
					key = directionEntity.id,
					value = directionEntity.name
				)
			}

			_state.value = DirectionsViewModelState.Content(
				directions = directionsMap
			)
		}

	}

}