package com.example.weektimetable.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weektimetable.domain.usecase.GetWeekTimetableByAuditoryUseCase
import com.example.weektimetable.domain.usecase.GetWeekTimetableByGroupUseCase
import com.example.weektimetable.domain.usecase.GetWeekTimetableByTeacherUseCase

class WeekTimetableViewModel(
	private val weekTimetableRouter: IWeekTimetableRouter,
	private val getWeekTimetableByGroupUseCase: GetWeekTimetableByGroupUseCase,
	private val getWeekTimetableByAuditoryUseCase: GetWeekTimetableByAuditoryUseCase,
	private val getWeekTimetableByTeacherUseCase: GetWeekTimetableByTeacherUseCase
): ViewModel() {

	private val _state = MutableLiveData<WeekTimetableState>()
	val state: LiveData<WeekTimetableState> = _state



}

