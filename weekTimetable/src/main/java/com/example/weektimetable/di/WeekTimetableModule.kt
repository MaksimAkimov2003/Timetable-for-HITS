package com.example.weektimetable.di

import com.example.weektimetable.presentation.WeekTimetableViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val weekTimetableModule = module {
	viewModel { WeekTimetableViewModel() }
}