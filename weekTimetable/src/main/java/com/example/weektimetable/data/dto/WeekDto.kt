package com.example.weektimetable.data.dto

import com.google.gson.annotations.SerializedName

data class WeekDto (
    @SerializedName("Days")
    val days: List<DayDto>?
)