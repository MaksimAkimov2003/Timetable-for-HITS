package com.example.directions_screen.di

import com.example.directions_screen.data.api.DirectionsApi
import com.example.directions_screen.data.dataSource.DirectionDataSource
import com.example.directions_screen.data.repository.DirectionsRepository
import com.example.directions_screen.domain.useCase.GetDirectionsListUseCase
import com.example.directions_screen.presentation.DirectionsViewModel
import com.example.network.changer.getRetrofit
import com.example.network.retrofit.createRetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val directionsScreenModule = module {
	viewModel {
		DirectionsViewModel(getDirectionsListUseCase = get())
	}
	factory { GetDirectionsListUseCase(repository = get()) }
	single { DirectionsRepository(dataSource = get()) }
	single { DirectionDataSource(api = get()) }
	factory<DirectionsApi> { createRetrofitService(retrofit = getRetrofit()) }
}