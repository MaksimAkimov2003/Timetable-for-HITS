package com.example.mainmenu.presentation

import androidx.lifecycle.ViewModel
import com.example.userstorage.domain.usecase.ClearUserDataUseCase
import com.example.userstorage.domain.usecase.GetUserDataUseCase

class MainMenuViewModel(
	private val clearUserDataUseCase: ClearUserDataUseCase,
	private val getUserDataUseCase: GetUserDataUseCase
) : ViewModel() {

	fun deleteData() {
		clearUserDataUseCase.invoke()
	}

	fun getData(): String {
		val data = getUserDataUseCase.invoke()

		return data.data.value
	}
}