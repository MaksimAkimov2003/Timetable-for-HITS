package com.example.weektimetable.data

data class DayDto (
    val weekDay: String,
    val countClasses: Int,
    val timeSlots: List<TimeSlotDto>
)