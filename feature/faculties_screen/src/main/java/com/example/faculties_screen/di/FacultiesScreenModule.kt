package com.example.faculties_screen.di

import com.example.faculties_screen.data.api.FacultiesApi
import com.example.faculties_screen.data.dataSource.FacultiesDataSource
import com.example.faculties_screen.data.repository.FacultiesRepository
import com.example.faculties_screen.domain.useCase.GetFacultiesListUseCase
import com.example.faculties_screen.presentation.FacultiesViewModel
import com.example.network.changer.getRetrofit
import com.example.network.retrofit.createRetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val facultiesScreenModule = module {
	viewModel {
		FacultiesViewModel(getFacultiesListUseCase = get())
	}
	factory { GetFacultiesListUseCase(repository = get()) }
	single { FacultiesRepository(dataSource = get()) }
	single { FacultiesDataSource(api = get()) }
	factory<FacultiesApi> { createRetrofitService(retrofit = getRetrofit()) }
}