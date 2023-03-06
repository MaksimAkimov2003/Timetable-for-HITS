package com.example.weektimetable.presentation

sealed class TimetableType {
    object Group: TimetableType()
    object Teacher: TimetableType()
    object Auditory: TimetableType()
}
