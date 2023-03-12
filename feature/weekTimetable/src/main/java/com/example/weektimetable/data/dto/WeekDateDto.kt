package com.example.weektimetable.data.dto

import com.google.gson.annotations.SerializedName

data class WeekDateDto(
    @SerializedName("StartDate")
    val startDate: String,
    @SerializedName("EndDate")
    val endDate: String
)
