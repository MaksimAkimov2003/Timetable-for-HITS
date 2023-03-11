package com.example.auditories_screen.di

import com.example.auditories_screen.data.api.AuditoriesApi
import com.example.auditories_screen.data.dataSource.AuditoriesDataSource
import com.example.auditories_screen.data.repository.AuditoriesRepository
import com.example.auditories_screen.domain.useCase.GetAuditoriesListUseCase
import com.example.auditories_screen.presentation.AuditoriesViewModel
import com.example.network.changer.getRetrofit
import com.example.network.retrofit.createRetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val auditoriesScreenModule = module {
	viewModel {
		AuditoriesViewModel(getAuditoriesListUseCase = get())
	}
	factory { GetAuditoriesListUseCase(repository = get()) }
	single { AuditoriesRepository(dataSource = get()) }
	single { AuditoriesDataSource(api = get()) }
	factory<AuditoriesApi> { createRetrofitService(retrofit = getRetrofit()) }
}