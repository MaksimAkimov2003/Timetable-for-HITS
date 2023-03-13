package com.example.weektimetable.presentation

import com.example.userstorage.domain.entity.TimetableType
import com.example.weektimetable.domain.entity.WeekDateEntity
import com.example.weektimetable.domain.entity.WeekEntity

sealed class WeekTimetableState {

    object Initial: WeekTimetableState()

    data class Loading(
        val isListTimetable: Boolean,
        val currentDay: Long,
        val timetableType: TimetableType,
        val currentDate: String,
        val currentWeek: WeekDateEntity
    ): WeekTimetableState()

    data class Error(
        val isListTimetable: Boolean,
        val currentDay: Long,
        val timetableType: TimetableType,
        val currentDate: String,
        val currentWeek: WeekDateEntity
    ): WeekTimetableState()

    data class Content(
        val isListTimetable: Boolean,
        val currentDay: Long,
        val timetableType: TimetableType,
        val currentDate: String,
        val currentWeek: WeekDateEntity,
        val timetable: WeekEntity
    ): WeekTimetableState()

}
