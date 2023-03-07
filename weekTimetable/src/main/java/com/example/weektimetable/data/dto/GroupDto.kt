package com.example.weektimetable.data.dto

import com.google.gson.annotations.SerializedName

data class GroupDto(
    @SerializedName("Number")
    val number: Int,
    @SerializedName("Direction")
    val direction: DirectionDto
)