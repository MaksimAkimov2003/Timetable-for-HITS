package com.example.weektimetable.data.dto

import com.google.gson.annotations.SerializedName

data class DayDto (
    @SerializedName("WeekDay")
    val weekDay: String,
    @SerializedName("Day")
    val day: String,
    @SerializedName("countClasses")
    val countClasses: Int,
    @SerializedName("timeSlotDTOs")
    val timeSlots: List<TimeSlotDto>
)