package com.example.feature.teachers_screen.di

import com.example.feature.teachers_screen.data.api.TeachersApi
import com.example.feature.teachers_screen.data.dataSource.TeachersDataSource
import com.example.feature.teachers_screen.data.repository.TeachersRepository
import com.example.feature.teachers_screen.domain.useCase.GetTeachersListUseCase
import com.example.feature.teachers_screen.presentation.TeachersViewModel
import com.example.network.changer.getRetrofit
import com.example.network.retrofit.createRetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val teachersScreenModule = module {
	viewModel {
		TeachersViewModel(getTeachersListUseCase = get(), saveUserDataUseCase = get())
	}
	factory { GetTeachersListUseCase(repository = get()) }
	single { TeachersRepository(dataSource = get()) }
	single { TeachersDataSource(api = get()) }
	factory<TeachersApi> { createRetrofitService(retrofit = getRetrofit()) }
}