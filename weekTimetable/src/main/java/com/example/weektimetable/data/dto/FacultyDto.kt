package com.example.weektimetable.data.dto

import com.google.gson.annotations.SerializedName

data class FacultyDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Number")
    val number: String
)
