package com.example.auditories_screen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auditories_screen.domain.useCase.GetAuditoriesListUseCase
import kotlinx.coroutines.launch

class AuditoriesViewModel(
	private val getAuditoriesListUseCase: GetAuditoriesListUseCase
) : ViewModel() {

	private val _state = MutableLiveData<AuditoriesViewModelState>(AuditoriesViewModelState.Loading)
	val state: LiveData<AuditoriesViewModelState> = _state

	fun getAuditories() {

		viewModelScope.launch {

			_state.value = AuditoriesViewModelState.Content(
				auditories = getAuditoriesListUseCase.execute().map { it.number }
			)
		}

	}

}