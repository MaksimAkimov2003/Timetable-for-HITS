package com.example.weektimetable.presentation

import com.example.weektimetable.domain.entity.WeekDateEntity
import com.example.weektimetable.domain.entity.WeekEntity

sealed class WeekTimetableState {

    object Initial: WeekTimetableState()

    data class Loading(
        val timetableType: TimetableType,
        val currentDate: String,
        val currentWeek: WeekDateEntity
    ): WeekTimetableState()

    data class Error(
        val timetableType: TimetableType,
        val currentDate: String,
        val currentWeek: WeekDateEntity
    ): WeekTimetableState()

    data class Content(
        val timetableType: TimetableType,
        val currentDate: String,
        val currentWeek: WeekDateEntity,
        val timetable: WeekEntity
    ): WeekTimetableState()

}
