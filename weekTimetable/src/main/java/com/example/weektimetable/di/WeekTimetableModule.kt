package com.example.weektimetable.di

import com.example.network.changer.getRetrofit
import com.example.network.retrofit.createRetrofitService
import com.example.weektimetable.data.api.WeekTimetableApi
import com.example.weektimetable.data.network.IWeekTimetableNetwork
import com.example.weektimetable.data.network.WeekTimetableNetwork
import com.example.weektimetable.data.repository.WeekTimetableRepository
import com.example.weektimetable.domain.repository.IWeekTimeTableRepository
import com.example.weektimetable.domain.usecase.GetWeekTimetableByAuditoryUseCase
import com.example.weektimetable.domain.usecase.GetWeekTimetableByGroupUseCase
import com.example.weektimetable.domain.usecase.GetWeekTimetableByTeacherUseCase
import com.example.weektimetable.presentation.WeekTimetableViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val weekTimetableModule = module {
	factory<WeekTimetableApi> { createRetrofitService(getRetrofit()) }
	factory<IWeekTimetableNetwork> { WeekTimetableNetwork(api = get()) }
	factory<IWeekTimeTableRepository> { WeekTimetableRepository(network = get()) }
	factory { GetWeekTimetableByGroupUseCase(repository = get()) }
	factory { GetWeekTimetableByAuditoryUseCase(repository = get()) }
	factory { GetWeekTimetableByTeacherUseCase(repository = get()) }
	viewModel { WeekTimetableViewModel(
		weekTimetableRouter = get(),
		getWeekTimetableByGroupUseCase = get(),
		getWeekTimetableByAuditoryUseCase = get(),
		getWeekTimetableByTeacherUseCase = get()
	) }
}