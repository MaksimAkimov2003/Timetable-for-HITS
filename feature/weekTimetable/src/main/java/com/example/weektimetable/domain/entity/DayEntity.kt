package com.example.weektimetable.domain.entity

data class DayEntity (
    val weekDay: String,
    val day: String,
    val countClasses: Int,
    val timeSlots: List<TimeSlotEntity>
)