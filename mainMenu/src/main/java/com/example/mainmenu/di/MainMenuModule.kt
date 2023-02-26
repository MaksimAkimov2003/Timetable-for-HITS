package com.example.mainmenu

import com.example.mainmenu.presentation.MainMenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainMenuModule = module {
	viewModel {
		MainMenuViewModel()
	}
}