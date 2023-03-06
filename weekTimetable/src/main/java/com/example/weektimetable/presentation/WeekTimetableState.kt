package com.example.weektimetable.presentation

import com.example.weektimetable.domain.entity.WeekEntity

sealed class WeekTimetableState {

    object Initial: WeekTimetableState()

    object Loading: WeekTimetableState()

    object Error: WeekTimetableState()

    data class Content(
        val timetableType: TimetableType,
        val timetable: WeekEntity
    ): WeekTimetableState()

}
